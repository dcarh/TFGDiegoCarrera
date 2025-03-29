package clients

import cats.effect.*
import modelClasses.errors.UserError.*
import org.http4s.*
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.implicits.*
import sttp.tapir.{DecodeResult, *}
import sttp.tapir.client.http4s.Http4sClientInterpreter

import scala.concurrent.duration.*

object GoogleBooksClient {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private var responseMaxSize = 1024 * 1024

  def setResponseMaxSize(size: Int): Unit =
    responseMaxSize = size

  def executeRequest[I, O](endpoint: PublicEndpoint[I, UserError, O, Any], resourceId: I): IO[Either[UserError, O]] = {
    val httpClientResource: Resource[IO, Client[IO]] =
      EmberClientBuilder.default[IO]
        .withChunkSize(responseMaxSize)
        .withTimeout(5.seconds)
        .build

    val (userRequest, parseResponse) = Http4sClientInterpreter[IO]()
      .toRequest(endpoint, baseUri = Some(uri"https://www.googleapis.com/books/v1"))
      .apply(resourceId)

    httpClientResource.use { httpClient =>
      httpClient.run(userRequest).use { response =>
        parseResponse(response).attempt.flatMap {
          case Right(DecodeResult.Value(Right(result))) => IO.pure(Right(result))
          case Right(DecodeResult.Value(Left(error)))   => IO.pure(Left(error))
          case Right(failure: DecodeResult.Failure)    => IO.pure(Left(Unknown(500, s"Failed to decode response: $failure")))
          case Left(error)                             => IO.pure(Left(Unknown(500, s"Failed to parse response: ${error.getMessage}")))
        }
      }.handleErrorWith { error =>
        IO.pure(Left(Unknown(500, s"Unexpected error: ${error.getMessage}")))
      }
    }
  }
}

