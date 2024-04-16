package api

import io.circe.generic.auto._
import modelClasses.Videogame
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class VideogamesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathVideogameId: EndpointInput[Videogame.Id] =
    path[Videogame.Id]("videogame_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonVideogameListOut: EndpointOutput[Seq[Videogame]] =
    jsonBody[Seq[Videogame]]

  private val jsonVideogameOut: EndpointOutput[Videogame] =
    jsonBody[Videogame]


  private val videogamesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "videogames")

  private val videogameBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "videogame")


  val videogamesEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    videogamesBaseEndpoint
      .name("Videogames endpoint")
      .description("This endpoint returns a list with all the videogames in the app")
      .get
      .in(queryOrderBy)
      .out(jsonVideogameListOut)

  val specificVideogameEndpoint: PublicEndpoint[Videogame.Id, Unit, Videogame, Any] =
    videogameBaseEndpoint
      .name("Specific videogame endpoint")
      .description("This endpoint returns a specific videogame by its Id")
      .get
      .in(pathVideogameId)
      .out(jsonVideogameOut)
}
