package endpoints.inputs

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*
import modelClasses.app.chatting.Message
import modelClasses.app.social.{Entry, Like, MediaList, Rating, Reply, Review}
import modelClasses.app.user.{User, UserProfile}
import modelClasses.ids.Chatting.*
import modelClasses.ids.Media.*
import modelClasses.ids.Social.*
import modelClasses.ids.User.UserId
import codecs.ModelClasses.Chatting.*
import codecs.ModelClasses.Media.*
import codecs.ModelClasses.Social.*
import codecs.ModelClasses.User.userIdCodec
import codecs.Others.*
import unionTypes.decoders.MediaDecodersForIDs.*
import unionTypes.encoders.MediaEncodersForIDs.*
import unionTypes.schemas.MediaSchemasForIDs.*
import unionTypes.encoders.SocialEncodersForIDs.*
import unionTypes.decoders.SocialDecodersForIDs.*
import unionTypes.schemas.SocialSchemasForIDs.*

object Common {

  object QueryInputs {

    val querySortBy: EndpointInput[Option[String]] =
      query[Option[String]]("sort_by")
      
    val queryArchived: EndpointInput[Option[Boolean]] =
      query[Option[Boolean]]("archived")

    val queryCategories: EndpointInput[Option[List[String]]] =
      query[Option[List[String]]]("categories").default(None)

    val querySearch: EndpointInput[String] =
      query[String]("query")
  }

  object PathInputs {

    val pathUserId: EndpointInput[UserId] =
      path[UserId]("user_id")

    val pathUsername: EndpointInput[String] =
      path[String]("username")

    val pathMovieId: EndpointInput[MovieId] =
      path[MovieId]("movie_id")

    val pathTvShowId: EndpointInput[TvShowId] =
      path[TvShowId]("tv_show_id")

    val pathSeasonNumber: EndpointInput[SeasonNumber] =
      path[SeasonNumber]("season_number")

    val pathEpisodeNumber: EndpointInput[EpisodeNumber] =
      path[EpisodeNumber]("episode_number")

    val pathVideogameId: EndpointInput[VideogameId] =
      path[VideogameId]("videogame_id")

    val pathBookId: EndpointInput[BookId] =
      path[BookId]("volumeId")

    val pathEntryId: EndpointInput[EntryId] =
      path[EntryId]("entry_id")
      
    val pathListId: EndpointInput[MediaListId] =
      path[MediaListId]("list_id")
      
    val pathReviewId: EndpointInput[ReviewId] =
      path[ReviewId]("review_id")
      
    val pathRatingId: EndpointInput[RatingId] =
      path[RatingId]("rating_id")
      
    val pathLikeId: EndpointInput[LikeId] =
      path[LikeId]("like_id")
      
    val pathReplyId: EndpointInput[ReplyId] =
      path[ReplyId]("reply_id")
      
    val pathChatId: EndpointInput[ChatId] =
      path[ChatId]("chat_id")
      
    val pathMessageId: EndpointInput[MessageId] =
      path[MessageId]("message_id")

    val pathField: EndpointInput[String] =
      path[String]("field")

    val pathAction: EndpointInput[String] =
      path[String]("action")
  }

  object JsonInputs {
      
    val jsonMessage: EndpointInput[Message] =
      jsonBody[Message]
      
    val jsonEntry: EndpointInput[Entry] =
      jsonBody[Entry]
      
    val jsonLike: EndpointInput[Like] =
      jsonBody[Like]
      
    val jsonMediaList: EndpointInput[MediaList] =
      jsonBody[MediaList]
      
    val jsonRating: EndpointInput[Rating] =
      jsonBody[Rating]
      
    val jsonReply: EndpointInput[Reply] =
      jsonBody[Reply]
      
    val jsonReview: EndpointInput[Review] =
      jsonBody[Review]
      
    val jsonUser: EndpointInput[User] =
      jsonBody[User]

    val jsonProfile: EndpointInput[UserProfile] =
      jsonBody[UserProfile]
  }

}
