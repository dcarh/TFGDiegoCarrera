package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.ErrorInfo
import modelClasses.app.chatting.{Chat, Message}
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{Entry, Like, MediaContentList, Rating, Reply, Review}
import modelClasses.app.user.{User, UserFavourites, UserSettings}
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import unionTypes.decoders.MediaDecoders.*
import unionTypes.decoders.MediaDecodersForIDs.*
import unionTypes.decoders.MediaDecodersAll.*
import unionTypes.decoders.SocialDecoders.*
import unionTypes.decoders.SocialDecodersForIDs.*
import unionTypes.encoders.MediaEncoders.*
import unionTypes.encoders.MediaEncodersForIDs.*
import unionTypes.encoders.MediaEncodersAll.*
import unionTypes.encoders.SocialEncoders.*
import unionTypes.encoders.SocialEncodersForIDs.*
import unionTypes.schemas.MediaSchemas.*
import unionTypes.schemas.MediaSchemasForIDs.*
import unionTypes.schemas.MediaSchemasAll.*
import unionTypes.schemas.SocialSchemas.*
import unionTypes.schemas.SocialSchemasForIDs.*

object Common {

  object ErrorOutputs {

//    val notFound:
//      String => EndpointOutput[ErrorInfo] =
//        obj => jsonBody[ErrorInfo].description(obj + " not found")

    val notFound: EndpointOutput[ErrorInfo] =
        jsonBody[ErrorInfo].description("Not found")

//    val invalidRequest: EndpointOutput[ErrorInfo] =
//        jsonBody[ErrorInfo].description("Invalid request")

    val invalidRequest: EndpointOutput[ErrorInfo] =
        jsonBody[ErrorInfo].description("Invalid Request")

    val unknown: EndpointOutput[ErrorInfo] =
        jsonBody[ErrorInfo].description("Unknown")
  }
  
  object ChattingOutputs {
    
    val chatSuccess: EndpointOutput[Chat] =
      jsonBody[Chat].description("The requested chat")

    val listOfChatsSuccess: EndpointOutput[List[Chat]] =
      jsonBody[List[Chat]].description("The requested list of chats")
      
    val messageSuccess: EndpointOutput[Message] =
      jsonBody[Message].description("The requested message")

    val listOfMessagesSuccess: EndpointOutput[List[Message]] =
      jsonBody[List[Message]].description("The requested list of messages")
  }

  object UserOutputs {

    val userSuccess: EndpointOutput[User] =
      jsonBody[User].description("The requested user")

    val listOfUsersSuccess: EndpointOutput[List[User]] =
      jsonBody[List[User]].description("The requested list of users")

    val userSettingsSuccess: EndpointOutput[UserSettings] =
      jsonBody[UserSettings].description("The requested settings of the user")
  }

  object SocialOutputs {

    val entrySuccess: EndpointOutput[Entry] =
      jsonBody[Entry].description("The requested entry")

    val reviewSuccess: EndpointOutput[Review] =
      jsonBody[Review].description("The requested review")

    val ratingSuccess: EndpointOutput[Rating] =
      jsonBody[Rating].description("The requested rating")

    val likeSuccess: EndpointOutput[Like] =
      jsonBody[Like].description("The requested like")

    val replySuccess: EndpointOutput[Reply] =
      jsonBody[Reply].description("The requested reply")

    val mediaContentListSucess: EndpointOutput[MediaContentList] =
      jsonBody[MediaContentList].description("The requested media content list")

    val listOfEntriesSuccess: EndpointOutput[List[Entry]] =
      jsonBody[List[Entry]].description("The requested list of entries")

    val listOfReviewsSuccess: EndpointOutput[List[Review]] =
      jsonBody[List[Review]].description("The requested list of reviews")

    val listOfRatingsSuccess: EndpointOutput[List[Rating]] =
      jsonBody[List[Rating]].description("The requested list of ratings")

    val listOfLikesSuccess: EndpointOutput[List[Like]] =
      jsonBody[List[Like]].description("The requested list of likes")

    val listOfRepliesSuccess: EndpointOutput[List[Reply]] =
      jsonBody[List[Reply]].description("The requested list of replies")

    val listOfMediaContentListSuccess: EndpointOutput[List[MediaContentList]] =
      jsonBody[List[MediaContentList]].description("The requested list of media content lists")

    val listOfLikeableObjectsSuccess: EndpointOutput[List[MediaContentList | Review | Reply]] =
      jsonBody[List[MediaContentList | Review | Reply]].description("The requested list of likeable objects")
  }

  object MediaOutputs {

    val movieSuccess: EndpointOutput[Movie] =
      jsonBody[Movie].description("The requested movie")

    val tvShowSuccess: EndpointOutput[TVShow] =
      jsonBody[TVShow].description("The requested TV show")

    val seasonSuccess: EndpointOutput[Season] =
      jsonBody[Season].description("The requested season")

    val episodeSuccess: EndpointOutput[Episode] =
      jsonBody[Episode].description("The requested episode")

    val videogameSuccess: EndpointOutput[Videogame] =
      jsonBody[Videogame].description("The requested videogame")

    val bookSuccess: EndpointOutput[Book] =
      jsonBody[Book].description("The requested book")

    val favouritesSuccess: EndpointOutput[UserFavourites] =
      jsonBody[UserFavourites].description("The requested object with the favourite media for a user")

    val listOfAllMediaSuccess: EndpointOutput[List[Movie | TVShow | Season | Episode | Videogame | Book]] =
      jsonBody[List[Movie | TVShow | Season | Episode | Videogame | Book]].description("The requested list of all media possible")

    val listOfProgressSuccess: EndpointOutput[List[TVShow | Season | Videogame | Book]] =
      jsonBody[List[TVShow | Season | Videogame | Book]].description("The requested list of the elements that can have a certain progress")

    val listOfPendingSuccess: EndpointOutput[List[Movie | TVShow | Season | Videogame | Book]] =
      jsonBody[List[Movie | TVShow | Season | Videogame | Book]].description("The requested list of the elements that a user can have pending")

    val listOfMoviesSuccess: EndpointOutput[List[Movie]] =
      jsonBody[List[Movie]].description("The requested list of movies")

    val listOfTvShowsSuccess: EndpointOutput[List[TVShow]] =
      jsonBody[List[TVShow]].description("The requested list of TV shows")

    val listOfSeasonsSuccess: EndpointOutput[List[Season]] =
      jsonBody[List[Season]].description("The requested list of seasons")

    val listOfEpisodesSuccess: EndpointOutput[List[Episode]] =
      jsonBody[List[Episode]].description("The requested list of episodes")

    val listOfVideogamesSuccess: EndpointOutput[List[Videogame]] =
      jsonBody[List[Videogame]].description("The requested list of videogames")

    val listOfBooksSuccess: EndpointOutput[List[Book]] =
      jsonBody[List[Book]].description("The requested list of books")
  }
}
