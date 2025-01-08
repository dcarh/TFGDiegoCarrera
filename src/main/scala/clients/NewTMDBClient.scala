//package clients
//
//import cats.effect.*
//import modelClasses.errors.UserError.*
//import modelClasses.ids.Media.{EpisodeNumber, MovieId, SeasonNumber, TvShowId}
//import modelClasses.tmdb.MovieRequests.RequestedMovie
//import org.http4s.*
//import org.http4s.client.Client
//import org.http4s.ember.client.EmberClientBuilder
//import org.http4s.implicits.*
//import sttp.tapir.{DecodeResult, *}
//import sttp.tapir.client.http4s.Http4sClientInterpreter
//
//import scala.concurrent.duration.*
////import retry._
////import retry.cats.effect._
////import scala.concurrent.duration._
//
//class NewTMDBClient {
//
//  private val apiKey = "6c004411738609c1a39b5f11582a04b7"
//  private val responseMaxSize = 1024 * 32576 * 64
//
//  def executeRequest[I, O](
//                            endpoint: PublicEndpoint[I, UserError, O, Any],
//                            resource:
//                            MovieId |
//                              TvShowId |
//                              (TvShowId, SeasonNumber) |
//                              (TvShowId, SeasonNumber, EpisodeNumber) |
//                              String
//                          ): Either[UserError, O] = {
//
//    try {
//      val httpClientResource: Resource[IO, Client[IO]] = EmberClientBuilder.default[IO]
//        .withMaxResponseHeaderSize(responseMaxSize)
//        .withChunkSize(responseMaxSize)
//        .withTimeout(5.seconds)
//        .build
//
//      httpClientResource.use { client =>
//        val result = (endpoint, resource) match {
//          case (endpoint: PublicEndpoint[(String, MovieId), _, _, _], movieId: MovieId) =>
//            println("Movie requested")
//            val (userRequest, parseResponse) =
//              Http4sClientInterpreter[IO]()
//                .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
//                .apply(apiKey, movieId)
//            (userRequest, parseResponse)
//
//          case (endpoint: PublicEndpoint[(String, TvShowId), _, _, _], tvShowId: TvShowId) =>
//            println("TV Show requested")
//            val (userRequest, parseResponse) =
//              Http4sClientInterpreter[IO]()
//                .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
//                .apply(apiKey, tvShowId)
//            (userRequest, parseResponse)
//
//          case (
//            endpoint: PublicEndpoint[(String, TvShowId, SeasonNumber), _, _, _],
//            seasonNumber: (TvShowId, SeasonNumber)
//            ) =>
//            println("Season requested")
//            val (userRequest, parseResponse) =
//              Http4sClientInterpreter[IO]()
//                .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
//                .apply(apiKey, seasonNumber._1, seasonNumber._2)
//            (userRequest, parseResponse)
//
//          case (
//            endpoint: PublicEndpoint[(String, TvShowId, SeasonNumber, EpisodeNumber), _, _, _],
//            episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber)
//            ) =>
//            println("Episode requested")
//            val (userRequest, parseResponse) =
//              Http4sClientInterpreter[IO]()
//                .toRequest(endpoint, baseUri = Some(uri"https://api.themoviedb.org/3"))
//                .apply(apiKey, episodeNumber._1, episodeNumber._2, episodeNumber._3)
//            (userRequest, parseResponse)
//
//          case _ =>
//            return Left(BadRequest("Wrong number of parameters for specified endpoint"))
//        }
//
//        val (userRequest, parseResponse) = result
//
//        println("Welcome to the http4s client interpreter example!")
//        println(s"The following request was derived from the endpoint definition: $userRequest")
//        println("Now we'll run the request against the real API...")
//
//        client.run(userRequest).use { response =>
//          IO {
//            parseResponse(response) match {
//              case DecodeResult.Value(Right(requestedResource)) =>
//                println(s"We received the requested resource: $requestedResource")
//                Right(requestedResource)
//
//              case DecodeResult.Value(Left(errorInfo)) =>
//                println(s"Error info received: $errorInfo")
//                Left(errorInfo)
//
//              case failure: DecodeResult.Failure =>
//                println(s"Failed to decode response: $failure")
//                Left(Unknown(500, s"Failed to decode response: $failure"))
//            }
//          }
//        }
//      }.attempt.unsafeRunSync() match {
//        case Left(error: Throwable) =>
//          println(s"An unexpected error occurred: ${error.getMessage}")
//          Left(Unknown(500, "An unexpected error occurred"))
//
//        case Right(result: Either[UserError, O]) => result
//      }
//    } catch {
//      case ex: Exception =>
//        println(s"Exception occurred: ${ex.getMessage}")
//        Left(Unknown(500, "An unexpected error occurred"))
//    }
//  }
//}
