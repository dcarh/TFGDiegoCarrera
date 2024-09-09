package clients

import cats.effect.*
import org.http4s.*
import org.http4s.implicits.*
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
import sttp.tapir.*
import sttp.tapir.DecodeResult
import sttp.tapir.client.http4s.Http4sClientInterpreter
import modelClasses.ErrorInfo
import modelClasses.ids.Media.{MovieId, TVShowId, SeasonNumber, EpisodeNumber}
import scala.concurrent.duration._
//import retry._
//import retry.cats.effect._
//import scala.concurrent.duration._

class TMDBClient {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val apiKey = "6c004411738609c1a39b5f11582a04b7"
  private val responseMaxSize = 1024 * 32576 * 64

  def executeRequest[I, O](
                               endpoint: PublicEndpoint[I, ErrorInfo, O, Any],
                               resourceId:
                                 MovieId |
                                 TVShowId |
                                 (TVShowId, SeasonNumber) |
                                 (TVShowId, SeasonNumber, EpisodeNumber)
                             ): IO[Either[ErrorInfo, O]] = {

    val httpClientResource: Resource[IO, Client[IO]] = EmberClientBuilder.default[IO]
      .withMaxResponseHeaderSize(responseMaxSize)
      .withChunkSize(responseMaxSize)
      .withTimeout(5.seconds)
      .build

    httpClientResource.use { client =>
      // Interpret the endpoint as a request and a response parser.
      val result = (endpoint, resourceId) match {

        case (endpoint: PublicEndpoint[(String, MovieId), _, _, _], movieId: MovieId) =>
          println("Movie requested")
          val (userRequest, parseResponse) =
            Http4sClientInterpreter[IO]()
              .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
              .apply(apiKey, movieId)
          IO.pure(userRequest, parseResponse)

        case (endpoint: PublicEndpoint[(String, TVShowId), _, _, _], tvShowId: TVShowId) =>
          println("TV Show requested")
          val (userRequest, parseResponse) =
            Http4sClientInterpreter[IO]()
              .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
              .apply(apiKey, tvShowId)
          IO.pure(userRequest, parseResponse)

        case (
          endpoint: PublicEndpoint[(String, TVShowId, SeasonNumber), _, _, _],
          seasonNumber: (TVShowId, SeasonNumber)) =>
          println("Season requested")
            val (userRequest, parseResponse) =
              Http4sClientInterpreter[IO]()
                .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
                .apply(apiKey, seasonNumber._1, seasonNumber._2)
            IO.pure(userRequest, parseResponse)

        case (
          endpoint: PublicEndpoint[(String, TVShowId, SeasonNumber, EpisodeNumber), _, _, _],
          episodeNumber: (TVShowId, SeasonNumber, EpisodeNumber)) =>
            println("Episode requested")
            val (userRequest, parseResponse) =
              Http4sClientInterpreter[IO]()
                .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
                .apply(apiKey, episodeNumber._1, episodeNumber._2, episodeNumber._3)
            IO.pure(userRequest, parseResponse)

        case _ => IO.pure(ErrorInfo("Wrong number of parameters for specified endpoint"))
      }

      result.flatMap {
        case (userRequest, parseResponse) =>
          for {
            _ <- IO(println("Welcome to the http4s client interpreter example!"))
            _ <- IO(println(s"The following request was derived from the endpoint definition: $userRequest"))
            _ <- IO(println(s"Now we'll run the request against the real API..."))
            response <- client.run(userRequest).use(IO.pure).attempt
            //response <- retryingOnAllErrors[Response[IO]](
            //  policy = retryPolicy,
            //  onError = (e: Throwable, details: RetryDetails) => IO(println(s"Request failed: ${e.getMessage}. Retrying..."))
            //)(client.run(userRequest).use(IO.pure))
            result <- response match {
              case Right(res) =>
                IO(println(s"We received the following response: $res")) >>
                  parseResponse(res).attempt.flatMap {
                    case Right(decodeResult) =>
                      decodeResult match {
                        case DecodeResult.Value(Right(requestedResource)) =>
                          IO.pure(Right(requestedResource))
                        case DecodeResult.Value(Left(errorInfo)) =>
                          IO(s"DecodeResult.Value.Left: $errorInfo") >>
                            IO.pure(Left(errorInfo))
                        case failure: DecodeResult.Failure =>
                          IO.pure(Left(ErrorInfo(s"Failed to decode response: $failure")))
                      }
                    case Left(error) =>
                      IO(println(s"Failed to parse response: ${error.toString}")) >>
                        IO.pure(Left(ErrorInfo("Failed to parse response")))
                  }
              case Left(error) =>
                IO(println(s"Request failed")) >>
                  IO.pure(Left(ErrorInfo("Request failed")))
            }
          } yield result
      }
    }.handleErrorWith { error =>
      IO.pure(println(s"An unexpected error occurred: ${error.getMessage}")).as(Left(ErrorInfo("An unexpected error occurred")))
    }
  }
}
