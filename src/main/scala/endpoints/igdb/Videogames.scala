package endpoints.igdb

import sttp.tapir.*
import endpoints.outputs.IGDB.*
import modelClasses.igdb.VideogameRequests.*
import modelClasses.errors.UserError.*

object Videogames {

  val requestVideogame: PublicEndpoint[(String, String, String, String), UserError, List[RequestedVideogame], Any] =
    Base.igdbBaseEndpoint(
        "requestVideogame",
        "This endpoint returns a list of videogames from IGDB API, whether we want a specific videogame by its ID " +
          "or a list of videogames that meet a certain criteria",
        "games"
      )
      .out(jsonListRequestedVideogameOut)

  val requestVideogameAllFields: PublicEndpoint[(String, String, String, String), UserError, List[VideogameAllFields], Any] =
    Base.igdbBaseEndpoint(
        "requestVideogameAllFields",
        "This endpoint returns a list of videogames from IGDB API, whether we want a specific videogame by its ID " +
          "or a list of videogames that meet a certain criteria",
        "games"
      )
      .out(jsonListRequestedVideogameAllFieldsOut)

}
