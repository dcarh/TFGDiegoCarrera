package endpoints.app.user.media

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.*

object UserDroppedMediaEndpoints {

  private val userDroppedBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("dropped")
  
  val getAllDroppedMedia:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "User's 'Dropped' media content endpoint",
        "This endpoint returns a list of all the 'Dropped' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addDroppedMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "Add 'Dropped' movie endpoint",
        "This endpoint adds a movie to the list of all the 'Dropped' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addDroppedTvShow:
    PublicEndpoint[(UserId, TvShowId), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "Add 'Dropped' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'Dropped' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTvShowId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addDroppedSeason:
    PublicEndpoint[(UserId, TvShowId, SeasonNumber), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "Add 'Dropped' season endpoint",
        "This endpoint adds a season to the list of all the 'Dropped' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addDroppedEpisode:
    PublicEndpoint[(UserId, TvShowId, SeasonNumber, EpisodeNumber), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "Add 'Dropped' episode endpoint",
        "This endpoint adds a episode to the list of all the 'Dropped' media content for a user",
        "PUT"
      )
        .in("add_episode")
        .in(PathInputs.pathTvShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addDroppedVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "Add 'Dropped' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'Dropped' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val addDroppedBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userDroppedBaseEndpoint(
        "Add 'Dropped' book endpoint",
        "This endpoint adds a book to the list of all the 'Dropped' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIdsOutput)

  val deleteDroppedMovie: PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userDroppedBaseEndpoint(
      "Delete 'Dropped' movie endpoint",
      "This endpoint deletes a movie to the list of all the 'Dropped' media content for a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteDroppedTvShow: PublicEndpoint[(UserId, TvShowId), UserError, Unit, Any] =
    userDroppedBaseEndpoint(
      "Delete 'Dropped' TV show endpoint",
      "This endpoint deletes a TV show to the list of all the 'Dropped' media content for a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTvShowId)

  val deleteDroppedSeason: PublicEndpoint[(UserId, TvShowId, SeasonNumber), UserError, Unit, Any] =
    userDroppedBaseEndpoint(
      "Delete 'Dropped' season endpoint",
      "This endpoint deletes a season to the list of all the 'Dropped' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathSeasonNumber)

  val deleteDroppedEpisode: PublicEndpoint[(UserId, TvShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
    userDroppedBaseEndpoint(
      "Delete 'Dropped' episode endpoint",
      "This endpoint deletes a episode to the list of all the 'Dropped' media content for a user",
      "DELETE"
    )
      .in("delete_episode")
      .in(PathInputs.pathTvShowId)
      .in(PathInputs.pathSeasonNumber)
      .in(PathInputs.pathEpisodeNumber)

  val deleteDroppedVideogame: PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
    userDroppedBaseEndpoint(
      "Delete 'Dropped' videogame endpoint",
      "This endpoint deletes a videogame to the list of all the 'Dropped' media content for a user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteDroppedBook: PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
    userDroppedBaseEndpoint(
      "Delete 'Dropped' book endpoint",
      "This endpoint deletes a book to the list of all the 'Dropped' media content for a user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
