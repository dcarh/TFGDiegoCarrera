package endpoints.io.outputs

import io.circe.*
import io.circe.generic.auto.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import codecs.decoders.MediaIDs.*
import codecs.encoders.MediaIDs.*

object IGDB {

  import domain.apis.igdb.VideogameRequests.*
    
  val jsonListRequestedVideogameOut: EndpointOutput[List[RequestedVideogame]] =
    jsonBody[List[RequestedVideogame]]  
    
  val jsonListRequestedVideogameAllFieldsOut: EndpointOutput[List[VideogameFromIGDB]] =
    jsonBody[List[VideogameFromIGDB]]
}