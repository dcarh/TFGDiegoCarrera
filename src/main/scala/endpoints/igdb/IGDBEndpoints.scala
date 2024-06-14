package endpoints.igdb

import io.circe.generic.auto.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*


class IGDBEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  case class VideogameRequested(id: Int, title: String, overview: String)

  // Define el endpoint para obtener información de un videojuego según su ID.
  val exampleGetVideogameEndpoint: PublicEndpoint[(String, String), Unit, VideogameRequested, Any] =
    endpoint
      .name("Get videogame from IGDB")
      .description("This endpoint a specific videogame from IGDB API by its ID")
      .get
      .in("movie")
      .in(path[String]("videogame_id"))
      .in(query[String]("api_key"))
      .out(jsonBody[VideogameRequested])

}
