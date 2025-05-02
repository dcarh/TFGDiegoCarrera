package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.errors.UserError.*
import modelClasses.app.chatting.{Chat, Message}
import modelClasses.app.media.{Book, TvEpisode, Movie, TvSeason, TvShow, Videogame}
import modelClasses.app.social.{Entry, Like, MediaList, Rating, Reply, Review}
import modelClasses.app.user.{User, UserFavourites, UserProfile}
import modelClasses.ids.Chatting.{ChatId, MessageId}
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.*
import modelClasses.ids.User.UserId
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import codecs.decoders.MediaUnionTypes.*
import codecs.decoders.MediaIDsUnionTypes.*
import codecs.decoders.AllMediaUnionType.*
import codecs.decoders.SocialUnionTypes.*
import codecs.decoders.SocialIDsUnionTypes.*
import codecs.encoders.MediaUnionTypes.*
import codecs.encoders.MediaIDsUnionTypes.*
import codecs.encoders.AllMediaUnionType.*
import codecs.encoders.SocialUnionTypes.*
import codecs.encoders.SocialIDsUnionTypes.*
import codecs.schemas.MediaUnionTypes.*
import codecs.schemas.MediaIDsUnionTypes.*
import codecs.schemas.AllMediaUnionType.*
import codecs.schemas.SocialUnionTypes.*
import codecs.schemas.SocialIDsUnionTypes.*

object Common {
  
  object ErrorOutputsTraits {

    val badRequestOutput: EndpointOutput[BadRequest] =
      stringBody.mapTo[BadRequest].description("Bad request")

    val notFoundOutput: EndpointOutput[NotFound] =
      stringBody.mapTo[NotFound].description("Not found")
        
    val unauthorizedOutput: EndpointOutput[Unauthorized] =
      stringBody.mapTo[Unauthorized].description("Unauthorized")

    val conflictOutput: EndpointOutput[Conflict] =
      stringBody.mapTo[Conflict].description("Conflict")

    val unknownOutput: EndpointOutput[Unknown] =
      jsonBody[Unknown].description("Unknown")

    val noContentOutput: EndpointOutput[NoContent] =
      jsonBody[NoContent].description("No content")
  }
  
  object ChattingOutputs {
    
    val chatOutput: EndpointOutput[Chat] =
      jsonBody[Chat].description("The requested chat")

    val listOfChatsOutput: EndpointOutput[List[Chat]] =
      jsonBody[List[Chat]].description("The requested list of chats")
      
    val listOfChatIdsOutput: EndpointOutput[List[ChatId]] =
      jsonBody[List[ChatId]].description("The requested list of chat IDs")
      
    val messageOutput: EndpointOutput[Message] =
      jsonBody[Message].description("The requested message")

    val listOfMessagesOutput: EndpointOutput[List[Message]] =
      jsonBody[List[Message]].description("The requested list of messages")

    val listOfMessagesIdsOutput: EndpointOutput[List[MessageId]] =
      jsonBody[List[MessageId]].description("The requested list of messages IDs")
  }

  object UserOutputs {

    val userOutput: EndpointOutput[User] =
      jsonBody[User].description("The requested user")

    val listOfUsersOutput: EndpointOutput[List[User]] =
      jsonBody[List[User]].description("The requested list of users")

    val listOfUserIdsOutput: EndpointOutput[List[UserId]] =
      jsonBody[List[UserId]].description("The requested list of user IDs")

    val tupleOfListsOfUserIdsOutput: EndpointOutput[(List[UserId], List[UserId])] =
      jsonBody[(List[UserId], List[UserId])].description("The requested tuple of lists of user IDs")

    val userProfileOutput: EndpointOutput[UserProfile] =
      jsonBody[UserProfile].description("The requested profile of the user")
  }

  object SocialOutputs {

    val entryOutput: EndpointOutput[Entry] =
      jsonBody[Entry].description("The requested entry")

    val reviewOutput: EndpointOutput[Review] =
      jsonBody[Review].description("The requested review")

    val ratingOutput: EndpointOutput[Rating] =
      jsonBody[Rating].description("The requested rating")

    val likeOutput: EndpointOutput[Like] =
      jsonBody[Like].description("The requested like")

    val replyOutput: EndpointOutput[Reply] =
      jsonBody[Reply].description("The requested reply")

    val mediaContentListOutput: EndpointOutput[MediaList] =
      jsonBody[MediaList].description("The requested media content list")

    val listOfEntriesOutput: EndpointOutput[List[Entry]] =
      jsonBody[List[Entry]].description("The requested list of entries")

    val listOfEntriesIdsOutput: EndpointOutput[List[EntryId]] =
      jsonBody[List[EntryId]].description("The requested list of entries IDs")

    val listOfReviewsOutput: EndpointOutput[List[Review]] =
      jsonBody[List[Review]].description("The requested list of reviews")

    val listOfReviewsIdsOutput: EndpointOutput[List[ReviewId]] =
      jsonBody[List[ReviewId]].description("The requested list of reviews IDs")

    val listOfRatingsOutput: EndpointOutput[List[Rating]] =
      jsonBody[List[Rating]].description("The requested list of ratings")

    val listOfRatingsIdsOutput: EndpointOutput[List[RatingId]] =
      jsonBody[List[RatingId]].description("The requested list of ratings IDs")

    val listOfLikesOutput: EndpointOutput[List[Like]] =
      jsonBody[List[Like]].description("The requested list of likes")

    val listOfLikesIdsOutput: EndpointOutput[List[LikeId]] =
      jsonBody[List[LikeId]].description("The requested list of likes IDs")

    val listOfRepliesOutput: EndpointOutput[List[Reply]] =
      jsonBody[List[Reply]].description("The requested list of replies")

    val listOfRepliesIdsOutput: EndpointOutput[List[ReplyId]] =
      jsonBody[List[ReplyId]].description("The requested list of replies IDs")

    val listOfMediaListsOutput: EndpointOutput[List[MediaList]] =
      jsonBody[List[MediaList]].description("The requested list of media lists")

    val listOfMediaListsIdsOutput: EndpointOutput[List[MediaListId]] =
      jsonBody[List[MediaListId]].description("The requested list of media lists IDs")

    val listOfLikeableObjectsOutput: EndpointOutput[List[MediaList | Review | Reply]] =
      jsonBody[List[MediaList | Review | Reply]].description("The requested list of likeable objects")
  }

  object MediaOutputs {

    val movieOutput: EndpointOutput[Movie] =
      jsonBody[Movie].description("The requested movie")

    val tvShowOutput: EndpointOutput[TvShow] =
      jsonBody[TvShow].description("The requested TV show")

    val seasonOutput: EndpointOutput[TvSeason] =
      jsonBody[TvSeason].description("The requested season")

    val episodeOutput: EndpointOutput[TvEpisode] =
      jsonBody[TvEpisode].description("The requested episode")

    val videogameOutput: EndpointOutput[Videogame] =
      jsonBody[Videogame].description("The requested videogame")

    val bookOutput: EndpointOutput[Book] =
      jsonBody[Book].description("The requested book")

    val favouritesOutput: EndpointOutput[UserFavourites] =
      jsonBody[UserFavourites].description("The requested object with the favourite media for a user")

    val listOfAllMediaOutput: EndpointOutput[List[Movie | TvShow | TvSeason | TvEpisode | Videogame | Book]] =
      jsonBody[List[Movie | TvShow | TvSeason | TvEpisode | Videogame | Book]].description("The requested list of all media possible")

    val listOfAllMediaIdsOutput: EndpointOutput[List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]] =
      jsonBody[List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]].description("The requested list of all media IDs possible")

    val listOfProgressOutput: EndpointOutput[List[TvShow | TvSeason | Videogame | Book]] =
      jsonBody[List[TvShow | TvSeason | Videogame | Book]].description("The requested list of the elements that can have a certain progress")
      
    val listOfProgressIdsOutput: EndpointOutput[List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]] =
      jsonBody[List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]].description("The requested list of the elements IDs that can have a certain progress")

    val listOfMoviesOutput: EndpointOutput[List[Movie]] =
      jsonBody[List[Movie]].description("The requested list of movies")

    val listOfTvShowsOutput: EndpointOutput[List[TvShow]] =
      jsonBody[List[TvShow]].description("The requested list of TV shows")

    val listOfSeasonsOutput: EndpointOutput[List[TvSeason]] =
      jsonBody[List[TvSeason]].description("The requested list of seasons")

    val listOfEpisodesOutput: EndpointOutput[List[TvEpisode]] =
      jsonBody[List[TvEpisode]].description("The requested list of episodes")

    val listOfVideogamesOutput: EndpointOutput[List[Videogame]] =
      jsonBody[List[Videogame]].description("The requested list of videogames")

    val listOfBooksOutput: EndpointOutput[List[Book]] =
      jsonBody[List[Book]].description("The requested list of books")
  }
}
