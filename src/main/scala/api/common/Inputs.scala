package api.common

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._

import modelClasses.media._
import modelClasses.user.{User, UserSettings}
import modelClasses.social.{Entry, MediaContentList, Review}

class Inputs {

  val querySortBy: EndpointInput[Option[String]] =
    query[Option[String]]("sort_by")

  val queryCategories: EndpointInput[Option[String]] =
    query[Option[String]]("categories")
    
  val querySearch: EndpointInput[String] =
    query[String]("search")

  val pathUserId: EndpointInput[User.Id] =
    path[User.Id]("user_id")

  val pathUsername: EndpointInput[String] =
    path[String]("username")
    
  // val pathSearch: EndpointInput[String] =
  //   path[String]("search")

  val pathMovieId: EndpointInput[Movie.Id] =
    path[Movie.Id]("movie_id")
    
  val pathTVShowId: EndpointInput[TVShow.Id] =
    path[TVShow.Id]("tv_show_id")
  
  val pathSeasonId: EndpointInput[Season.Id] =
    path[Season.Id]("season_id")

  val pathEpisodeId: EndpointInput[Episode.Id] =
    path[Episode.Id]("episode_id")

  val pathVideogameId: EndpointInput[Videogame.Id] =
    path[Videogame.Id]("videogame_id")

  val pathBookId: EndpointInput[Book.Id] =
    path[Book.Id]("book_id")

  val pathListId: EndpointInput[MediaContentList.Id] =
    path[MediaContentList.Id]("list_id")

  val pathEntryId: EndpointInput[Entry.Id] =
    path[Entry.Id]("entry_id")

  val pathReviewId: EndpointInput[Review.Id] =
    path[Review.Id]("review_id")

  val jsonSettingsIn: EndpointInput[UserSettings] =
    jsonBody[UserSettings]

  val jsonElementListIn: EndpointInput[MediaContentList] =
    jsonBody[MediaContentList]
}

object Inputs {
  val inputs = new Inputs()
}
