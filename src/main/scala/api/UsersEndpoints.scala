package api

import model.{Movie, User}
import cats.effect.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Printer
import sttp.tapir.model.UsernamePassword
import java.util.UUID

class UsersEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]


  val queryId: EndpointInput[Long] =
    query[Long]("id")

  val pathUsername: EndpointInput[String] =
    path[String]("username")

  val queryUsername: EndpointInput[String] =
    query[String]("username")

  val pathMovies: EndpointInput[String] =
    path[String]("movies")

  val pathTvShows: EndpointInput[String] =
    path[String]("tv_shows")

  val pathSeasons: EndpointInput[String] =
    path[String]("seasons")

  val pathEpisodes: EndpointInput[String] =
    path[String]("episodes")

  val pathVideogames: EndpointInput[String] =
    path[String]("videogames")

  val pathBooks: EndpointInput[String] =
    path[String]("books")

  val pathLists: EndpointInput[String] =
    path[String]("lists")

  val pathLikes: EndpointInput[String] =
    path[String]("likes")

  val pathReviews: EndpointInput[String] =
    path[String]("reviews")

  val pathReplies: EndpointInput[String] =
    path[String]("replies")

  val pathCompleted: EndpointInput[String] =
    path[String]("completed")

  val pathInProgress: EndpointInput[String] =
    path[String]("in_progress")

  val pathOnHold: EndpointInput[String] =
    path[String]("on_hold")

  val pathAbandoned: EndpointInput[String] =
    path[String]("abandoned")

  val pathPending: EndpointInput[String] =
    path[String]("pending")

  val pathWishlist: EndpointInput[String] =
    path[String]("wishlist")

  val pathOwned: EndpointInput[String] =
    path[String]("owned")

  val userEndpoint: PublicEndpoint[Long, Unit, User, Any] =
    endpoint.in("users").in(queryId).out(jsonBody[User])

  val usersListEndpoint: PublicEndpoint[String, Unit, List[User], Any] =
    endpoint.in("users" / "search").in(queryUsername).out(jsonBody[List[User]])

  val userMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    endpoint.in("user").in(pathUsername).out(jsonBody[List[Movie]])

}
