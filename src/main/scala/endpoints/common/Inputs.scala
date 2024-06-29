package endpoints.common

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.media.IDs._
import modelClasses.app.social.{Entry, MediaContentList, Review}
import modelClasses.app.user.{User, UserSettings}
import modelClasses.app.media.*
import codecs.ModelClasses.*

object Inputs {
  
  object QueryInputs {

    val querySortBy: EndpointInput[Option[String]] =
      query[Option[String]]("sort_by")

    val queryCategories: EndpointInput[Option[String]] =
      query[Option[String]]("categories")

    val querySearch: EndpointInput[String] =
      query[String]("query")

    val queryApiKey: EndpointInput[String] =
      query[String]("api_key")
  }
  
  object PathInputs {
    
    val pathUserId: EndpointInput[User.Id] =
      path[User.Id]("user_id")

    val pathUsername: EndpointInput[String] =
      path[String]("username")

    // val pathSearch: EndpointInput[String] =
    //   path[String]("search")

    val pathMovieId: EndpointInput[Movie.Id] =
      path[Movie.Id]("movie_id")

    val pathTVShowId: EndpointInput[TVShow.Id] =
      path[TVShow.Id]("series_id")
      
    val pathMovieIdNew: EndpointInput[MovieId] =
      path[MovieId]("movie_id")

    val pathTVShowIdNew: EndpointInput[TVShowId] =
      path[TVShowId]("series_id")

    val pathSeasonNumber: EndpointInput[Season.Number] =
      path[Season.Number]("season_number")

    val pathSeasonNumberNew: EndpointInput[SeasonNumber] =
      path[SeasonNumber]("season_number")

    val pathEpisodeNumber: EndpointInput[Episode.Number] =
      path[Episode.Number]("episode_number")

    val pathEpisodeNumberNew: EndpointInput[EpisodeNumber] =
      path[EpisodeNumber]("episode_number")

    val pathVideogameId: EndpointInput[Videogame.Id] =
      path[Videogame.Id]("videogame_id")

    val pathVideogameIdNew: EndpointInput[VideogameId] =
      path[VideogameId]("videogame_id")

    val pathBookId: EndpointInput[Book.Id] =
      path[Book.Id]("book_id")

    val pathBookIdNew: EndpointInput[BookId] =
      path[BookId]("volumeId")

    val pathListId: EndpointInput[MediaContentList.Id] =
      path[MediaContentList.Id]("list_id")

    val pathEntryId: EndpointInput[Entry.Id] =
      path[Entry.Id]("entry_id")

    val pathReviewId: EndpointInput[Review.Id] =
      path[Review.Id]("review_id")
  }
  
  object JsonInputs {
    
    val jsonSettingsIn: EndpointInput[UserSettings] =
      jsonBody[UserSettings]

    val jsonElementListIn: EndpointInput[MediaContentList] =
      jsonBody[MediaContentList]
  }
}
