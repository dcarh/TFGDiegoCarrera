package endpoints.app

import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.server.http4s.Http4sServerInterpreter

import cats.effect.IO

import org.http4s.HttpRoutes

object ExampleEndpoint {

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("user")

  private val pathUserId: EndpointInput[Long] =
    path[Long]("user_id")

  private val jsonLongListOut: EndpointOutput[List[Long]] =
    jsonBody[List[Long]]

  
  val favouritesEndpoint: PublicEndpoint[Long, String, List[Long], Any] =
    userBaseEndpoint
      .name("User Favourites endpoint")
      .description("This endpoint returns the favourite elements IDs of the specified user")
      .get
      .in(pathUserId)
      .in("favourites")
      .out(jsonLongListOut)
      .errorOut(stringBody)


  val favouritesEndpointLogic: Long => IO[Either[String, List[Long]]] = 
    id =>
      if (id == 1) IO.pure(Right(List(502033, 61222, 113112, 12354)))
      else IO.pure(Left("Item not found"))


  val returnFavouritesRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(favouritesEndpoint.serverLogic(favouritesEndpointLogic))

}
