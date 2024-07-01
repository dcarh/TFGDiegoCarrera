package endpoints.inputs


import sttp.model.HeaderNames
import sttp.tapir.*

object IGDB {

  object Headers {

    val authorization: EndpointInput[String] =
      header(HeaderNames.Authorization)

    val authorizationOriginal: EndpointInput[String] =
      header[String]("Authorization")

    val clientId: EndpointInput[String] =
      header[String]("Client-ID")

    val accept: EndpointInput[String] =
      header(HeaderNames.Accept)

    val acceptOriginal: EndpointInput[String] =
      header[String]("Accept")

  }

  object Body {

    val stringBody: EndpointInput[String] =
      stringJsonBody
  }

}
