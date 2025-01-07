package endpoints.app.user.media

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserOnHoldMediaEndpoints {

  private val userOnHoldBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("on_hold")


  val getOnHold:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "User's 'On Hold' media content endpoint",
        "This endpoint returns a list of all the 'On Hold' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addOnHoldTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addOnHoldSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' season endpoint",
        "This endpoint adds a season to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addOnHoldVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val addOnHoldBook:
    PublicEndpoint[(UserId, BookId), UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' book endpoint",
        "This endpoint adds a book to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfProgressIdsOutput)

  val deleteOnHoldTvShow: PublicEndpoint[(UserId, TVShowId), UserError, Unit, Any] =
    userOnHoldBaseEndpoint(
      "Delete 'On Hold' TV show endpoint",
      "This endpoint deletes a TV show to the list of all the 'On Hold' media content for a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTVShowId)

  val deleteOnHoldSeason: PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, Unit, Any] =
    userOnHoldBaseEndpoint(
      "Delete 'On Hold' season endpoint",
      "This endpoint deletes a season to the list of all the 'On Hold' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)

  val deleteOnHoldVideogame: PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
    userOnHoldBaseEndpoint(
      "Delete 'On Hold' videogame endpoint",
      "This endpoint deletes a videogame to the list of all the 'On Hold' media content for a user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteOnHoldBook: PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
    userOnHoldBaseEndpoint(
      "Delete 'On Hold' book endpoint",
      "This endpoint deletes a book to the list of all the 'On Hold' media content for a user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
