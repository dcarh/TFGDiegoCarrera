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

  def executeRequest[I, O](
                            endpoint: PublicEndpoint[I, UserError, O, Any],
                            resourceId:
                              VideogameId |
                              String
                          ): IO[Either[UserError, O]] = {


    val httpClientResource: Resource[IO, Client[IO]] = EmberClientBuilder.default[IO]
      .withChunkSize(responseMaxSize)
      .withTimeout(5.seconds)
      .build

    httpClientResource.use { client =>
      // Interpret the endpoint as a request and a response parser.
      val result = (endpoint, resourceId) match {

        case (endpoint: PublicEndpoint[(String, String, String, String), _, _, _], id: VideogameId) =>
          val bodyQuery = "fields *;where id = " + id.value + ";sort first_release_date desc;limit 100;"
          println("Videogame requested")
          val (userRequest, parseResponse) =
            Http4sClientInterpreter[IO]()
              .toRequest(endpoint, baseUri = Some(uri"https://api.igdb.com/v4"))
              .apply(headerAccept, headerClientID, headerAuthorization, bodyQuery)
          IO.pure(userRequest, parseResponse)

        case (endpoint: PublicEndpoint[(String, String, String, String), _, _, _], name: String) =>
          val bodyQuery = "fields *;where name ~ \"" + name + "\";sort first_release_date desc;limit 100;"
          println("Videogame requested")
          val (userRequest, parseResponse) =
            Http4sClientInterpreter[IO]()
              .toRequest(endpoint, baseUri = Some(uri"https://api.igdb.com/v4"))
              .apply(headerAccept, headerClientID, headerAuthorization, bodyQuery)
          IO.pure(userRequest, parseResponse)

        case _ => IO.pure(BadRequest("Wrong number of parameters for specified endpoint"))
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
                          IO.pure(Left(Unknown(500, s"Failed to decode response: $failure")))
                      }
                    case Left(error) =>
                      IO(println(s"Failed to parse response: ${error.toString}")) >>
                        IO.pure(Left(Unknown(500, s"Failed to parse response: ${error.toString}")))
                  }
              case Left(error) =>
                IO(println(s"Request failed: ${error.toString}")) >>
                  IO.pure(Left(BadRequest(s"Request failed: ${error.toString}")))
            }
          } yield result
          
        case badRequest: BadRequest => IO.pure(Left(BadRequest("Bad Request: " + badRequest.what)))
      }
    }.handleErrorWith { error =>
      IO.pure(println(s"An unexpected error occurred: ${error.getMessage}")).as(Left(Unknown(500, s"An unexpected error occurred: ${error.toString}")))
    }
  }
}
