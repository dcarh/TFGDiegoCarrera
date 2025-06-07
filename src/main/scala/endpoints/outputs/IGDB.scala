package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import domain.igdb._
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import codecs.decoders.MediaIDs.*
import codecs.encoders.MediaIDs.*

object IGDB {

  import VideogameRequests.*
    
  val jsonListRequestedVideogameOut: EndpointOutput[List[RequestedVideogame]] =
    jsonBody[List[RequestedVideogame]]  
    
  val jsonListRequestedVideogameAllFieldsOut: EndpointOutput[List[VideogameFromIGDB]] =
    jsonBody[List[VideogameFromIGDB]]
}