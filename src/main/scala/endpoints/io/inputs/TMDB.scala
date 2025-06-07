package endpoints.io.inputs

import sttp.tapir.*

object TMDB {
  
  object Query {

    val queryApiKey: EndpointInput[String] =
      query[String]("api_key")
  }

}
