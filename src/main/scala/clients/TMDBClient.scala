package clients

import cats.effect.*
import org.http4s.*
import org.http4s.implicits.*
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
import sttp.tapir.*
import sttp.tapir.DecodeResult
import sttp.tapir.client.http4s.Http4sClientInterpreter
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId}

import scala.concurrent.duration.*
//import retry._
//import retry.cats.effect._
//import scala.concurrent.duration._

object TMDBClient {

  private val apiKey = "6c004411738609c1a39b5f11582a04b7"
  private val responseMaxSize = 1024 * 1024
  private val baseUri = uri"https://api.themoviedb.org/3"

  def executeRequest[I, O](
                            endpoint: PublicEndpoint[I, UserError, O, Any],
                            resource: MovieId |
                                      TvShowId |
                                      (TvShowId, TvSeasonNumber) |
                                      (TvShowId, TvSeasonNumber, TvEpisodeNumber) |
                                      String
                          ): IO[Either[UserError, O]] = {

    val httpClientResource: Resource[IO, Client[IO]] =
      EmberClientBuilder.default[IO]
        .withChunkSize(responseMaxSize)
        .withTimeout(5.seconds)
        .build

    val requestResult = (endpoint, resource) match
      case (endpoint: PublicEndpoint[(String, MovieId), _, _, _], movieId: MovieId) =>
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, baseUri = Some(baseUri)).apply(apiKey, movieId))

      case (endpoint: PublicEndpoint[(String, TvShowId), _, _, _], tvShowId: TvShowId) =>
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, baseUri = Some(baseUri)).apply(apiKey, tvShowId))

      case (endpoint: PublicEndpoint[(String, TvShowId, TvSeasonNumber), _, _, _], (tvShowId: TvShowId, tvSeasonNumber: TvSeasonNumber)) =>
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, baseUri = Some(baseUri)).apply(apiKey, tvShowId, tvSeasonNumber))

      case (endpoint: PublicEndpoint[(String, TvShowId, TvSeasonNumber, TvEpisodeNumber), _, _, _], (tvShowId: TvShowId, tvSeasonNumber: TvSeasonNumber, tvEpisodeNumber: TvEpisodeNumber)) =>
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, baseUri = Some(baseUri)).apply(apiKey, tvShowId, tvSeasonNumber, tvEpisodeNumber))

      case (endpoint: PublicEndpoint[(String, String), _, _, _], title: String) =>
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, baseUri = Some(baseUri)).apply(apiKey, title))

      case _ => Left(BadRequest("Wrong number of parameters for specified endpoint"))

    requestResult match {
      case Left(error) => IO.pure(Left(error))

      case Right((userRequest, parseResponse)) =>
        httpClientResource.use { httpClient =>
          httpClient.run(userRequest).use { response =>
            parseResponse(response).attempt.flatMap {
              case Right(DecodeResult.Value(Right(result))) => IO.pure(Right(result))
              case Right(DecodeResult.Value(Left(error))) => IO.pure(Left(error))
              case Right(failure: DecodeResult.Failure) => IO.pure(Left(Unknown(500, s"Failed to decode response: $failure")))
              case Left(error) => IO.pure(Left(Unknown(500, s"Failed to parse response: ${error.getMessage}")))

            }
          }.handleErrorWith { error =>
            IO.pure(Left(Unknown(500, s"Unexpected error: ${error.getMessage}")))
          }
        }
    }
  }
}