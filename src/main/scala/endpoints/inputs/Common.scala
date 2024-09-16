package endpoints.inputs

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

import io.circe.generic.auto.*

import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{Entry, MediaContentList, Review}
import modelClasses.app.user.{User, UserSettings}
import modelClasses.app.media.*

import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, MediaContentListId, ReviewId}
import modelClasses.ids.User.UserId

import codecs.ModelClasses.Media.*
import codecs.ModelClasses.Social.*
import codecs.ModelClasses.User.*

import schemas.UnionTypes.*
import schemas.UnionTypesForIds.*

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

    // val pathUserId: EndpointInput[User.Id] =
    //   path[User.Id]("user_id")

    val pathUserId: EndpointInput[UserId] =
      path[UserId]("user_id")

    val pathUsername: EndpointInput[String] =
      path[String]("username")

    // val pathSearch: EndpointInput[String] =
    //   path[String]("search")

    // val pathMovieId: EndpointInput[Movie.Id] =
    //   path[Movie.Id]("movie_id")

    val pathMovieId: EndpointInput[MovieId] =
      path[MovieId]("movie_id")

    // val pathTVShowId: EndpointInput[TVShow.Id] =
    //   path[TVShow.Id]("series_id")

    val pathTVShowId: EndpointInput[TVShowId] =
      path[TVShowId]("series_id")

    // val pathSeasonNumber: EndpointInput[Season.Number] =
    //   path[Season.Number]("season_number")

    val pathSeasonNumber: EndpointInput[SeasonNumber] =
      path[SeasonNumber]("season_number")

    // val pathEpisodeNumber: EndpointInput[Episode.Number] =
    //   path[Episode.Number]("episode_number")

    val pathEpisodeNumber: EndpointInput[EpisodeNumber] =
      path[EpisodeNumber]("episode_number")

    // val pathVideogameId: EndpointInput[Videogame.Id] =
    //   path[Videogame.Id]("videogame_id")

    val pathVideogameId: EndpointInput[VideogameId] =
      path[VideogameId]("videogame_id")

    // val pathBookId: EndpointInput[Book.Id] =
    //   path[Book.Id]("book_id")

    val pathBookId: EndpointInput[BookId] =
      path[BookId]("volumeId")

    // val pathEntryId: EndpointInput[Entry.Id] =
    //   path[Entry.Id]("entry_id")

    val pathEntryId: EndpointInput[EntryId] =
      path[EntryId]("entry_id")

    // val pathListId: EndpointInput[MediaContentList.Id] =
    //   path[MediaContentList.Id]("list_id")
      
    val pathListId: EndpointInput[MediaContentListId] =
      path[MediaContentListId]("list_id")

    // val pathReviewId: EndpointInput[Review.Id] =
    //   path[Review.Id]("review_id")
      
    val pathReviewId: EndpointInput[ReviewId] =
      path[ReviewId]("review_id")
  }

  object JsonInputs {

    val jsonSettingsIn: EndpointInput[UserSettings] =
      jsonBody[UserSettings]

    val jsonElementListIn: EndpointInput[MediaContentList] =
      jsonBody[MediaContentList]
  }

}
