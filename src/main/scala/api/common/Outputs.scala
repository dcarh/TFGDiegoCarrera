package api.common

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

import io.circe.generic.auto._

import modelClasses.media._
import modelClasses.social._
import modelClasses.user._
import modelClasses.ErrorInfo
import modelClasses.chatting.Chat

class Outputs {

  val jsonMovieOut: EndpointOutput[Movie] =
    jsonBody[Movie]

  val jsonTVShowOut: EndpointOutput[TVShow] =
    jsonBody[TVShow]

  val jsonSeasonOut: EndpointOutput[Season] =
    jsonBody[Season]

  val jsonEpisodeOut: EndpointOutput[Episode] =
    jsonBody[Episode]

  val jsonVideogameOut: EndpointOutput[Videogame] =
    jsonBody[Videogame]

  val jsonBookOut: EndpointOutput[Book] =
    jsonBody[Book]

  val jsonEntryOut: EndpointOutput[Entry] =
    jsonBody[Entry]

  val jsonReviewOut: EndpointOutput[Review] =
    jsonBody[Review]

  val jsonReplyOut: EndpointOutput[Reply] =
    jsonBody[Reply]

  val jsonUserOut: EndpointOutput[User] =
    jsonBody[User]

  val jsonChatOut: EndpointOutput[Chat] =
    jsonBody[Chat]

  val jsonMediaContentListOut: EndpointOutput[MediaContentList] =
    jsonBody[MediaContentList]

  val jsonEitherMediaListOut: EndpointOutput[List[Either[MediaMainContent, MediaSecondaryContent]]] =
    jsonBody[List[Either[MediaMainContent, MediaSecondaryContent]]]

  val jsonMovieListOut: EndpointOutput[List[Movie]] =
    jsonBody[List[Movie]]

  val jsonTVShowListOut: EndpointOutput[List[TVShow]] =
    jsonBody[List[TVShow]]

  val jsonSeasonListOut: EndpointOutput[List[Season]] =
    jsonBody[List[Season]]

  val jsonEpisodeListOut: EndpointOutput[List[Episode]] =
    jsonBody[List[Episode]]

  val jsonVideogameListOut: EndpointOutput[List[Videogame]] =
    jsonBody[List[Videogame]]

  val jsonBookListOut: EndpointOutput[List[Book]] =
    jsonBody[List[Book]]

  val jsonEntryListOut: EndpointOutput[List[Entry]] =
    jsonBody[List[Entry]]

  val jsonReviewListOut: EndpointOutput[List[Review]] =
    jsonBody[List[Review]]

  val jsonReplyListOut: EndpointOutput[List[Reply]] =
    jsonBody[List[Reply]]

  val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  val jsonChatListOut: EndpointOutput[List[Chat]] =
    jsonBody[List[Chat]]

  val jsonListOfMediaContentListOut: EndpointOutput[List[MediaContentList]] =
    jsonBody[List[MediaContentList]]

  val jsonSettingsOut: EndpointOutput[UserSettings] =
    jsonBody[UserSettings]

  val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
    jsonBody[ErrorInfo]
}

object Outputs {
  val outputs = new Outputs()
}
