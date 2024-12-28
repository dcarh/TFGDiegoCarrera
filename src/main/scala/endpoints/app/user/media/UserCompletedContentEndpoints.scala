package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserCompletedContentEndpoints {

  private val userCompletedBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("completed")
      
  val getCompleted:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "User's 'Completed' media content endpoint",
        "This endpoint returns a list of all the 'Completed' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIds)
  // TODO: Decidir si la lógica de esta sección (y similares) va a ser devolver todo y filtrar/ordenar con query params o si hacerlo con múltiples endpoints como los de abajo (me decanto por lo primero)

  val addCompletedMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "Add 'Completed' movie endpoint",
        "This endpoint adds a movie to the list of all the 'Completed' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addCompletedTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "Add 'Completed' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'Completed' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addCompletedSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "Add 'Completed' season endpoint",
        "This endpoint adds a season to the list of all the 'Completed' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addCompletedEpisode:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "Add 'Completed' episode endpoint",
        "This endpoint adds a episode to the list of all the 'Completed' media content for a user",
        "PUT"
      )
        .in("add_episode")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addCompletedVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "Add 'Completed' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'Completed' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addCompletedBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userCompletedBaseEndpoint(
        "Add 'Completed' book endpoint",
        "This endpoint adds a book to the list of all the 'Completed' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIds)

  val deleteCompletedMovie:
    PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userCompletedBaseEndpoint(
      "Delete 'Completed' movie endpoint",
      "This endpoint deletes a movie to the list of all the 'Completed' media content for a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteCompletedTvShow: PublicEndpoint[(UserId, TVShowId), UserError, Unit, Any] =
    userCompletedBaseEndpoint(
      "Delete 'Completed' TV show endpoint",
      "This endpoint deletes a TV show to the list of all the 'Completed' media content for a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTVShowId)

  val deleteCompletedSeason: PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, Unit, Any] =
    userCompletedBaseEndpoint(
      "Delete 'Completed' season endpoint",
      "This endpoint deletes a season to the list of all the 'Completed' media content for a user",
      "DELETE"
    )
      .in("delete_season")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)

  val deleteCompletedEpisode: PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
    userCompletedBaseEndpoint(
      "Delete 'Completed' episode endpoint",
      "This endpoint deletes a episode to the list of all the 'Completed' media content for a user",
      "DELETE"
    )
      .in("delete_episode")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)
      .in(PathInputs.pathEpisodeNumber)

  val deleteCompletedVideogame: PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
    userCompletedBaseEndpoint(
      "Delete 'Completed' videogame endpoint",
      "This endpoint deletes a videogame to the list of all the 'Completed' media content for a user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteCompletedBook: PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
    userCompletedBaseEndpoint(
      "Delete 'Completed' book endpoint",
      "This endpoint deletes a book to the list of all the 'Completed' media content for a user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

  //  val userCompletedMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' movies endpoint",
//      "This endpoint returns a list of all the 'Completed' movies for a user",
//      "GET"
//    )
//      .in("movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userCompletedTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' TV shows endpoint",
//      "This endpoint returns a list of all the 'Completed' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userCompletedSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' seasons endpoint",
//      "This endpoint returns a list of all the 'Completed' seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userCompletedEpisodesListEndpoint: PublicEndpoint[UserId, UserError, List[Episode], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' episodes endpoint",
//      "This endpoint returns a list of all the 'Completed' episodes for a user",
//      "GET"
//    )
//      .in("episodes")
//      .out(MediaOutputs.listOfEpisodesSuccess)
//
//  val userCompletedVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' videogames endpoint",
//      "This endpoint returns a list of all the 'Completed' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userCompletedBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' books endpoint",
//      "This endpoint returns a list of all the 'Completed' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
