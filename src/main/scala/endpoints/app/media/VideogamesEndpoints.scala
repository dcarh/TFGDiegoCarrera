package endpoints.app.media

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._

import modelClasses.app.media.Videogame
import modelClasses.ids.Media.VideogameId

object VideogamesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val videogamesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "videogames")

  private val videogameBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "videogame")
  
  val videogamesEndpoint: PublicEndpoint[Option[String], Unit, List[Videogame], Any] =
    videogamesBaseEndpoint
      .name("Videogames endpoint")
      .description("This endpoint returns a list with all the videogames in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonVideogameListOut)

  val specificVideogameEndpoint: PublicEndpoint[VideogameId, Unit, Videogame, Any] =
    videogameBaseEndpoint
      .name("Specific videogame endpoint")
      .description("This endpoint returns a specific videogame by its Id")
      .get
      .in(PathInputs.pathVideogameId)
      .out(MediaOutputs.jsonVideogameOut)
}
