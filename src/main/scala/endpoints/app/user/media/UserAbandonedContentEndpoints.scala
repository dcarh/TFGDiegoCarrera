package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.*

object UserAbandonedContentEndpoints {

  private val userAbandonedBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("abandoned")
  
  val getAbandoned:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "User's 'Abandoned' media content endpoint",
        "This endpoint returns a list of all the 'Abandoned' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIds)
    // TODO: Decidir si la lógica de esta sección (y similares) va a ser devolver todo y filtrar/ordenar con query params o si hacerlo con múltiples endpoints como los de abajo (me decanto por lo primero)

  val addAbandonedMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "Add 'Abandoned' movie endpoint",
        "This endpoint adds a movie to the list of all the 'Abandoned' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addAbandonedTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "Add 'Abandoned' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'Abandoned' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addAbandonedSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "Add 'Abandoned' season endpoint",
        "This endpoint adds a season to the list of all the 'Abandoned' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addAbandonedEpisode:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "Add 'Abandoned' season endpoint",
        "This endpoint adds a season to the list of all the 'Abandoned' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addAbandonedVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "Add 'Abandoned' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'Abandoned' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addAbandonedBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userAbandonedBaseEndpoint(
        "Add 'Abandoned' book endpoint",
        "This endpoint adds a book to the list of all the 'Abandoned' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIds)

  val deleteAbandonedMovie:
    PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
      userAbandonedBaseEndpoint(
        "Delete 'Abandoned' movie endpoint",
        "This endpoint deletes a movie to the list of all the 'Abandoned' media content for a user",
        "DELETE"
      )
        .in("delete_movie")
        .in(PathInputs.pathMovieId)

  val deleteAbandonedTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, Unit, Any] =
      userAbandonedBaseEndpoint(
        "Delete 'Abandoned' TV show endpoint",
        "This endpoint deletes a TV show to the list of all the 'Abandoned' media content for a user",
        "DELETE"
      )
        .in("delete_tv_show")
        .in(PathInputs.pathTVShowId)

  val deleteAbandonedSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, Unit, Any] =
      userAbandonedBaseEndpoint(
        "Delete 'Abandoned' season endpoint",
        "This endpoint deletes a season to the list of all the 'Abandoned' media content for a user",
        "DELETE"
      )
        .in("delete_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)

  val deleteAbandonedEpisode:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
      userAbandonedBaseEndpoint(
        "Delete 'Abandoned' season endpoint",
        "This endpoint deletes a season to the list of all the 'Abandoned' media content for a user",
        "DELETE"
      )
        .in("delete_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)

  val deleteAbandonedVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
      userAbandonedBaseEndpoint(
        "Delete 'Abandoned' videogame endpoint",
        "This endpoint deletes a videogame to the list of all the 'Abandoned' media content for a user",
        "DELETE"
      )
        .in("delete_videogame")
        .in(PathInputs.pathVideogameId)

  val deleteAbandonedBook:
    PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
      userAbandonedBaseEndpoint(
        "Delete 'Abandoned' book endpoint",
        "This endpoint deletes a book to the list of all the 'Abandoned' media content for a user",
        "DELETE"
      )
        .in("delete_book")
        .in(PathInputs.pathBookId)

//  val userAbandonedMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' movies endpoint",
//      "This endpoint returns a list of all the 'Abandoned' movies for a user",
//      "GET"
//    )
//      .in("movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userAbandonedTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' TV shows endpoint",
//      "This endpoint returns a list of all the 'Abandoned' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userAbandonedSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' TV seasons endpoint",
//      "This endpoint returns a list of all the 'Abandoned' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//  
//  val userAbandonedEpisodesListEndpoint: PublicEndpoint[UserId, UserError, List[Episode], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' TV episodes endpoint",
//      "This endpoint returns a list of all the 'Abandoned' TV episodes for a user",
//      "GET"
//    )
//      .in("episodes")
//      .out(MediaOutputs.listOfEpisodesSuccess)
//
//  val userAbandonedVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' videogames endpoint",
//      "This endpoint returns a list of all the 'Abandoned' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userAbandonedBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' books endpoint",
//      "This endpoint returns a list of all the 'Abandoned' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
