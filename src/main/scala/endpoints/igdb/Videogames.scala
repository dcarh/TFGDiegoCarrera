package endpoints.igdb

import sttp.tapir.*

import endpoints.inputs.Common._
import endpoints.inputs.IGDB._
import endpoints.outputs.IGDB._
import modelClasses.app.media.IDs.VideogameId
import modelClasses.igdb.VideogameRequests._
import modelClasses.ErrorInfo

object Videogames {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  val requestVideogameEndpoint: PublicEndpoint[(String, String, String, String), ErrorInfo, RequestedVideogame, Any] =
    Base.igdbBaseEndpoint(
        "Get videogame from IGDB",
        "This endpoint returns a specific videogame from IGDB API by its ID"
      )
      .in("games")
      .out(jsonRequestedVideogameOut)
  
  val requestVideogamesListEndpoint: PublicEndpoint[(String, String, String, String), ErrorInfo, RequestedVideogamesList, Any] =
    Base.igdbBaseEndpoint(
        "Get videogame from IGDB",
        "This endpoint returns a specific videogame from IGDB API by its ID"
      )
      .in("games")
      .out(jsonRequestedVideogamesListOut)

}
