package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.igdb._
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object IGDB {

  import VideogameRequests.*

  val jsonRequestedVideogameOut: EndpointOutput[RequestedVideogame] =
    jsonBody[RequestedVideogame]
    
  val jsonRequestedVideogamesListOut: EndpointOutput[RequestedVideogamesList] = 
    jsonBody[RequestedVideogamesList]

}