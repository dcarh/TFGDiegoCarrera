package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.ErrorInfo
import modelClasses.app.chatting.{Chat, Message}
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{Entry, Like, MediaContentList, Rating, Reply, Review}
import modelClasses.app.user.{User, UserSettings}
import schemas.UnionTypes.*
import schemas.UnionTypesForIds.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object Common {

  object ApiOutputs {

    val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
      jsonBody[ErrorInfo]
  }
  
  object ChattingOutputs {
    
    val jsonChatOut: EndpointOutput[Chat] =
      jsonBody[Chat]

    val jsonChatListOut: EndpointOutput[List[Chat]] =
      jsonBody[List[Chat]]
      
    val jsonMessageOut: EndpointOutput[Message] =
      jsonBody[Message]

    val jsonMessageListOut: EndpointOutput[List[Message]] =
      jsonBody[List[Message]]
  }

  object UserOutputs {

    val jsonUserOut: EndpointOutput[User] =
      jsonBody[User]

    val jsonUserListOut: EndpointOutput[List[User]] =
      jsonBody[List[User]]

    val jsonSettingsOut: EndpointOutput[UserSettings] =
      jsonBody[UserSettings]
  }

  object SocialOutputs {

    val jsonEntryOut: EndpointOutput[Entry] =
      jsonBody[Entry]

    val jsonReviewOut: EndpointOutput[Review] =
      jsonBody[Review]

    val jsonRatingOut: EndpointOutput[Rating] =
      jsonBody[Rating]

    val jsonLikeOut: EndpointOutput[Like] =
      jsonBody[Like]

    val jsonReplyOut: EndpointOutput[Reply] =
      jsonBody[Reply]

    val jsonMediaContentListOut: EndpointOutput[MediaContentList] =
      jsonBody[MediaContentList]

    val jsonEntryListOut: EndpointOutput[List[Entry]] =
      jsonBody[List[Entry]]

    val jsonReviewListOut: EndpointOutput[List[Review]] =
      jsonBody[List[Review]]

    val jsonRatingListOut: EndpointOutput[List[Rating]] =
      jsonBody[List[Rating]]

    val jsonLikeListOut: EndpointOutput[List[Like]] =
      jsonBody[List[Like]]

    val jsonReplyListOut: EndpointOutput[List[Reply]] =
      jsonBody[List[Reply]]

    val jsonListOfMediaContentListOut: EndpointOutput[List[MediaContentList]] =
      jsonBody[List[MediaContentList]]
  }

  object MediaOutputs {

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

    val jsonAllMediaListOut: EndpointOutput[List[Movie | TVShow | Season | Episode | Videogame | Book]] =
      jsonBody[List[Movie | TVShow | Season | Episode | Videogame | Book]]

    val jsonMediaListOut1: EndpointOutput[List[TVShow | Season | Videogame | Book]] =
      jsonBody[List[TVShow | Season | Videogame | Book]]

    val jsonMediaListOut2: EndpointOutput[List[Movie | TVShow | Season | Videogame | Book]] =
      jsonBody[List[Movie | TVShow | Season | Videogame | Book]]

    val jsonFavouritesOut: EndpointOutput[List[Movie | TVShow | Videogame | Book]] =
      jsonBody[List[Movie | TVShow | Videogame | Book]]

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
  }
  
  object OtherOutputs {
    val jsonListLikeablesOutput: EndpointOutput[List[Movie | TVShow | Season | Episode | Videogame | Book | MediaContentList | Review | Reply]] =
      jsonBody[List[Movie | TVShow | Season | Episode | Videogame | Book | MediaContentList | Review | Reply]]
  }

}
