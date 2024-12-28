package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserInProgressContentEndpoints {

  private val userInProgressBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("in_progress")


  val getInProgress:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "User's 'In Progress' media content endpoint",
        "This endpoint returns a list of all the 'In Progress' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIds)
  // TODO: Decidir si la lógica de esta sección (y similares) va a ser devolver todo y filtrar/ordenar con query params o si hacerlo con múltiples endpoints como los de abajo (me decanto por lo primero)

  val addInProgressMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' movie endpoint",
        "This endpoint adds a movie to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addInProgressTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addInProgressSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' season endpoint",
        "This endpoint adds a season to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addInProgressEpisode:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' season endpoint",
        "This endpoint adds a season to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addInProgressVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addInProgressBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userInProgressBaseEndpoint(
        "Add 'In Progress' book endpoint",
        "This endpoint adds a book to the list of all the 'In Progress' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIds)

  val deleteInProgressMovie: PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' movie endpoint",
      "This endpoint deletes a movie to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteInProgressTvShow: PublicEndpoint[(UserId, TVShowId), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' TV show endpoint",
      "This endpoint deletes a TV show to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTVShowId)

  val deleteInProgressSeason: PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' season endpoint",
      "This endpoint deletes a season to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)

  val deleteInProgressEpisode: PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
    userInProgressBaseEndpoint(
      "Delete 'In Progress' season endpoint",
      "This endpoint deletes a season to the list of all the 'In Progress' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)
      .in(PathInputs.pathEpisodeNumber)

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

  //  val userInProgressTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userInProgressBaseEndpoint(
//      "User's 'In Progress' TV shows endpoint",
//      "This endpoint returns a list of all the 'In Progress' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userInProgressSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userInProgressBaseEndpoint(
//      "User's 'In Progress' TV seasons endpoint",
//      "This endpoint returns a list of all the 'In Progress' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userInProgressVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userInProgressBaseEndpoint(
//      "User's 'In Progress' videogames endpoint",
//      "This endpoint returns a list of all the 'In Progress' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userInProgressBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userInProgressBaseEndpoint(
//      "User's 'In Progress' books endpoint",
//      "This endpoint returns a list of all the 'In Progress' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
