package endpoints.inputs


import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*

object TMDB {
  
  object Query {

    val queryApiKey: EndpointInput[String] =
      query[String]("api_key")
  }

}
