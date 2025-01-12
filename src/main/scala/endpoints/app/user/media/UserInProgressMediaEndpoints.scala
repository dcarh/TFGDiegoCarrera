package endpoints.app.user.media

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserInProgressMediaEndpoints {

  private val userInProgressBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("in_progress")


  val getAllInProgressMedia:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "User's 'In Progress' media content endpoint",
        "This endpoint returns a list of all the 'In Progress' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfProgressIdsOutput)
      
  val addInProgressTvShow:
    PublicEndpoint[(UserId, TvShowId), UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTvShowId)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addInProgressSeason:
    PublicEndpoint[(UserId, TvShowId, SeasonNumber), UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' season endpoint",
        "This endpoint adds a season to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addInProgressVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addInProgressBook:
    PublicEndpoint[(UserId, BookId), UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' book endpoint",
        "This endpoint adds a book to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val deleteInProgressTvShow: PublicEndpoint[(UserId, TvShowId), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' TV show endpoint",
      "This endpoint deletes a TV show to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTvShowId)

  val deleteInProgressSeason: PublicEndpoint[(UserId, TvShowId, SeasonNumber), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' season endpoint",
      "This endpoint deletes a season to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathSeasonNumber)

  val deleteInProgressVideogame: PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' videogame endpoint",
      "This endpoint deletes a videogame to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteInProgressBook: PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' book endpoint",
      "This endpoint deletes a book to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
