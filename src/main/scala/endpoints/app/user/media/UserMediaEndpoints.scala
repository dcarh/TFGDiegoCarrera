package endpoints.app.user.media

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.*
import modelClasses.ids.User.UserId
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
        "This endpoint returns a media content list from a field of the user",
        "GET"
      )
        .in(PathInputs.pathField)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addMovie:
    PublicEndpoint[(UserId, String, MovieId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addMovie",
        "This endpoint adds a movie to a media content list from the fields of the user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addTvShow:
    PublicEndpoint[(UserId, String, TvShowId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addTvShow",
        "This endpoint adds a TV show to a media content list from the fields of the user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTvShowId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addSeason:
    PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addSeason",
        "This endpoint adds a season to a media content list from the fields of the user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathTvSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addEpisode:
    PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addEpisode",
        "This endpoint adds a episode to a media content list from the fields of the user",
        "PUT"
      )
        .in("add_episode")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathTvSeasonNumber)
        .in(PathInputs.pathTvEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addVideogame:
    PublicEndpoint[(UserId, String, VideogameId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addVideogame",
        "This endpoint adds a videogame to a media content list from the fields of the user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addBook:
    PublicEndpoint[(UserId, String, BookId), UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId], Any] =
      userMediaBaseEndpoint(
        "addBook",
        "This endpoint adds a book to a media content list from the fields of the user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val deleteMovie: PublicEndpoint[(UserId, String, MovieId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteMovie",
      "This endpoint deletes a movie form a media content list from the fields of the user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteTvShow: PublicEndpoint[(UserId, String, TvShowId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteTvShow",
      "This endpoint deletes a TV show from a media content list from the fields of the user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTvShowId)

  val deleteSeason: PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteSeason",
      "This endpoint deletes a season from a media content list from the fields of the user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathTvSeasonNumber)

  val deleteEpisode: PublicEndpoint[(UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteEpisode",
      "This endpoint deletes a episode from a media content list from the fields of the user",
      "DELETE"
    )
      .in("delete_episode")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathTvSeasonNumber)
      .in(PathInputs.pathTvEpisodeNumber)

  val deleteVideogame: PublicEndpoint[(UserId, String, VideogameId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteVideogame",
      "This endpoint deletes a videogame from a media content list from the fields of the user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteBook: PublicEndpoint[(UserId, String, BookId), UserError, Unit, Any] =
    userMediaBaseEndpoint(
      "deleteBook",
      "This endpoint deletes a book from a media content list from the fields of the user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
