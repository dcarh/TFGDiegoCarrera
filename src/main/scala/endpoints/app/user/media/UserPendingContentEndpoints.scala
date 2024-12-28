package endpoints.app.user

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
        .out(MediaOutputs.listOfAllMediaIds)
  // TODO: Decidir si la lógica de esta sección (y similares) va a ser devolver todo y filtrar/ordenar con query params o si hacerlo con múltiples endpoints como los de abajo (me decanto por lo primero)

  val addPendingMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' movie endpoint",
        "This endpoint adds a movie to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addPendingTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfAllMediaIds)

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
        .out(MediaOutputs.listOfAllMediaIds)

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
        .out(MediaOutputs.listOfAllMediaIds)

  val addPendingVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addPendingBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userPendingBaseEndpoint(
        "Add 'Pending' book endpoint",
        "This endpoint adds a book to the list of all the 'Pending' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIds)

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

  //  val userPendingMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' movies endpoint",
//      "This endpoint returns a list of all the 'Pending' movies for a user",
//      "GET"
//    )
//      .in("movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userPendingTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' TV shows endpoint",
//      "This endpoint returns a list of all the 'Pending' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userPendingSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' TV seasons endpoint",
//      "This endpoint returns a list of all the 'Pending' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userPendingVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' videogames endpoint",
//      "This endpoint returns a list of all the 'Pending' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userPendingBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' books endpoint",
//      "This endpoint returns a list of all the 'Pending' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
