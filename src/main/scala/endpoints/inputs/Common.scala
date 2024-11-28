package endpoints.inputs

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

import io.circe.generic.auto.*

import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{Entry, MediaContentList, Review}
import modelClasses.app.user.{User, UserSettings}
import modelClasses.app.media.*

import modelClasses.ids.Chatting.*
import modelClasses.ids.Media.*
import modelClasses.ids.Social.*
import modelClasses.ids.User.UserId

import codecs.ModelClasses.Chatting.*
import codecs.ModelClasses.Media.*
import codecs.ModelClasses.Social.*
import codecs.ModelClasses.User.*

import unionTypes.decoders.MediaDecodersForIDs.*
import unionTypes.encoders.MediaEncodersForIDs.*
import unionTypes.schemas.MediaSchemasForIDs.*

object Common {

  object QueryInputs {

    val querySortBy: EndpointInput[Option[String]] =
      query[Option[String]]("sort_by")

    val queryCategories: EndpointInput[Option[String]] =
      query[Option[String]]("categories")

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

    val pathTVShowId: EndpointInput[TVShowId] =
      path[TVShowId]("series_id")

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
      
    val pathListId: EndpointInput[MediaContentListId] =
      path[MediaContentListId]("list_id")
      
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
  }

  object JsonInputs {

    val jsonSettingsIn: EndpointInput[UserSettings] =
      jsonBody[UserSettings]

    val jsonElementListIn: EndpointInput[MediaContentList] =
      jsonBody[MediaContentList]
  }

}
