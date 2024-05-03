package api.common

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*
import modelClasses.user.{User, UserSettings}
import modelClasses.social.{MediaContentList, Review}

class Inputs {

  val pathUserId: EndpointInput[User.Id] =
    path[User.Id]("user_id")

  val pathUsername: EndpointInput[String] =
    path[String]("username")

  val pathListId: EndpointInput[MediaContentList.Id] =
    path[MediaContentList.Id]("list_id")


  val pathReviewId: EndpointInput[Review.Id] =
    path[Review.Id]("review_id")

  val queryType: EndpointInput[String] =
    query[String]("type")

  val queryOrderBy: EndpointInput[String] =
    query[String]("order_by")

  val pathMovies: EndpointInput[String] =
    path[String]("movies")

  val pathTVShows: EndpointInput[String] =
    path[String]("tv_shows")

  val pathSeasons: EndpointInput[String] =
    path[String]("seasons")

  val pathEpisodes: EndpointInput[String] =
    path[String]("episodes")

  val pathVideogames: EndpointInput[String] =
    path[String]("videogames")

  val pathBooks: EndpointInput[String] =
    path[String]("books")

  val jsonSettingsIn: EndpointInput[UserSettings] =
    jsonBody[UserSettings]

  val jsonElementListIn: EndpointInput[MediaContentList] =
    jsonBody[MediaContentList]

  val pathLists: EndpointInput[String] =
    path[String]("lists")

  val pathUsers: EndpointInput[String] =
    path[String]("users")
}

object Inputs {
  val inputs = new Inputs()
}
