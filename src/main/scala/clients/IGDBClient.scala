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
import modelClasses.ids.Media.VideogameId
import scala.concurrent.duration._
//import retry._
//import retry.cats.effect._
//import scala.concurrent.duration._

object IGDBClient {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val responseMaxSize = 1024 * 1024

  private val headerAccept = "application/json"
  private val headerClientID = "qn2w238rb9gpxxpiv546tgg9th31mk"
  private val headerAuthorization = "Bearer 0bbvtu41ss3duzr13wv4xje3fh59vk"
  private val baseUri = uri"https://api.igdb.com/v4"

  def executeRequest[I, O](
                            endpoint: PublicEndpoint[I, UserError, O, Any],
                            resource: VideogameId | String
                          ): IO[Either[UserError, O]] = {

    val httpClientResource: Resource[IO, Client[IO]] =
      EmberClientBuilder.default[IO]
        .withChunkSize(responseMaxSize)
        .withTimeout(5.seconds)
        .build

    val requestResult = (endpoint, resource) match {
      case (endpoint: PublicEndpoint[(String, String, String, String), _, _, _], id: VideogameId) =>
        val bodyQuery = s"fields *;where id = " + id.value + ";sort first_release_date desc;limit 100;"
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, Some(baseUri)).apply(headerAccept, headerClientID, headerAuthorization, bodyQuery))

      case (endpoint: PublicEndpoint[(String, String, String, String), _, _, _], name: String) =>
        val bodyQuery = s"fields *;where name ~ \"" + name + "\";sort first_release_date desc;limit 100;"
        Right(Http4sClientInterpreter[IO]().toRequest(endpoint, Some(baseUri)).apply(headerAccept, headerClientID, headerAuthorization, bodyQuery))

      case _ => Left(BadRequest("Invalid resource type"))
    }

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
