package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.igdb._
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object IGDB {

  import VideogameRequests.*
    
  val jsonListRequestedVideogameOut: EndpointOutput[List[RequestedVideogame]] =
    jsonBody[List[RequestedVideogame]]
}