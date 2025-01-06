package endpoints.app.user.media

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserPendingContentEndpoints {

  private val userPendingBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("pending")


  val getPending:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "User's 'Pending' media content endpoint",
        "This endpoint returns a list of all the 'Pending' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addPendingMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' movie endpoint",
        "This endpoint adds a movie to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addPendingTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addPendingSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' season endpoint",
        "This endpoint adds a season to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addPendingEpisode:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' episode endpoint",
        "This endpoint adds a episode to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_episode")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addPendingVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addPendingBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' book endpoint",
        "This endpoint adds a book to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val deletePendingMovie: PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userPendingBaseEndpoint(
      "Delete 'Pending' movie endpoint",
      "This endpoint deletes a movie to the list of all the 'Pending' media content for a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deletePendingTvShow: PublicEndpoint[(UserId, TVShowId), UserError, Unit, Any] =
    userPendingBaseEndpoint(
      "Delete 'Pending' TV show endpoint",
      "This endpoint deletes a TV show to the list of all the 'Pending' media content for a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTVShowId)

  val deletePendingSeason: PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, Unit, Any] =
    userPendingBaseEndpoint(
      "Delete 'Pending' season endpoint",
      "This endpoint deletes a season to the list of all the 'Pending' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)

  val deletePendingEpisode: PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
    userPendingBaseEndpoint(
      "Delete 'Pending' episode endpoint",
      "This endpoint deletes a episode to the list of all the 'Pending' media content for a user",
      "DELETE"
    )
      .in("delete_episode")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)
      .in(PathInputs.pathEpisodeNumber)

  val deletePendingVideogame: PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
    userPendingBaseEndpoint(
      "Delete 'Pending' videogame endpoint",
      "This endpoint deletes a videogame to the list of all the 'Pending' media content for a user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deletePendingBook: PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
    userPendingBaseEndpoint(
      "Delete 'Pending' book endpoint",
      "This endpoint deletes a book to the list of all the 'Pending' media content for a user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
