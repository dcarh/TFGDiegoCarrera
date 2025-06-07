package endpoints.app.user.media

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.inputs.Common.*
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.ids.Media.*
import domain.ids.User.UserId
import sttp.tapir.*

object UserMediaEndpoints {

  private val userMediaBaseEndpoint:
    (String, String, String) => PublicEndpoint[(UserId, String), UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in(PathInputs.pathField)

  val getAllMedia:
    PublicEndpoint[
      (UserId, String, Option[List[String]]), 
      UserError, 
      List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
      Any] =
    specificUserBaseEndpoint(
        "getAllMedia",
        "This endpoint returns all the media marked by a user as 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "GET"
      )
        .in(PathInputs.pathField)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addMovie:
    PublicEndpoint[(UserId, String, MovieId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addMovie",
        "This endpoint adds a movie to one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addTvShow:
    PublicEndpoint[(UserId, String, TvShowId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addTvShow",
        "This endpoint adds a TV show to one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTvShowId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addTvSeason:
    PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addTvSeason",
        "This endpoint adds a season to one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "PUT"
      )
        .in("add_tv_season")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathTvSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addTvEpisode:
    PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addTvEpisode",
        "This endpoint adds a episode to one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "PUT"
      )
        .in("add_tv_episode")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathTvSeasonNumber)
        .in(PathInputs.pathTvEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addVideogame:
    PublicEndpoint[(UserId, String, VideogameId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addVideogame",
        "This endpoint adds a videogame to one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addBook:
    PublicEndpoint[(UserId, String, BookId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addBook",
        "This endpoint adds a book to one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val deleteMovie: PublicEndpoint[(UserId, String, MovieId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteMovie",
      "This endpoint deletes a movie from one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteTvShow: PublicEndpoint[(UserId, String, TvShowId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteTvShow",
      "This endpoint deletes a TV show from one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTvShowId)

  val deleteTvSeason: PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteTvSeason",
      "This endpoint deletes a season from one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
      "DELETE"
    )
      .in("delete_tv_season")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathTvSeasonNumber)

  val deleteTvEpisode: PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteTvEpisode",
      "This endpoint deletes a episode from one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
      "DELETE"
    )
      .in("delete_tv_episode")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathTvSeasonNumber)
      .in(PathInputs.pathTvEpisodeNumber)

  val deleteVideogame: PublicEndpoint[(UserId, String, VideogameId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteVideogame",
      "This endpoint deletes a videogame from one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteBook: PublicEndpoint[(UserId, String, BookId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteBook",
      "This endpoint deletes a book from one of the following fields of a user: 'Completed', 'In Progress', 'On Hold', 'Dropped' or 'Pending'",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
