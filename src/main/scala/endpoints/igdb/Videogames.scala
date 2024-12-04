package endpoints.igdb

import sttp.tapir.*

import endpoints.outputs.IGDB._

import modelClasses.igdb.VideogameRequests._
import modelClasses.ErrorInfo

object Videogames {

  val requestVideogameEndpoint: PublicEndpoint[(String, String, String, String), ErrorInfo, List[RequestedVideogame], Any] =
    Base.igdbBaseEndpoint(
        "Get videogame from IGDB",
        "This endpoint returns a list of videogames from IGDB API, whether we want a specific videogame by its ID " +
          "or a list of videogames that meet a certain criteria",
        "games"
      )
      .out(jsonListRequestedVideogameOut)

}
