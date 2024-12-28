package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserOnHoldContentEndpoints {

  private val userOnHoldBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("on_hold")


  val getOnHold:
    PublicEndpoint[(UserId, Option[String], Option[List[String]]), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "User's 'On Hold' media content endpoint",
        "This endpoint returns a list of all the 'On Hold' media content for a user",
        "GET"
      )
        .in(QueryInputs.querySortBy)
        .in(QueryInputs.queryCategories)
        .out(MediaOutputs.listOfAllMediaIds)
  // TODO: Decidir si la lógica de esta sección (y similares) va a ser devolver todo y filtrar/ordenar con query params o si hacerlo con múltiples endpoints como los de abajo (me decanto por lo primero)

  val addOnHoldMovie:
    PublicEndpoint[(UserId, MovieId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' movie endpoint",
        "This endpoint adds a movie to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_movie")
        .in(PathInputs.pathMovieId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addOnHoldTvShow:
    PublicEndpoint[(UserId, TVShowId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' TV show endpoint",
        "This endpoint adds a TV show to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_tv_show")
        .in(PathInputs.pathTVShowId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addOnHoldSeason:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' season endpoint",
        "This endpoint adds a season to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_season")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addOnHoldEpisode:
    PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' episode endpoint",
        "This endpoint adds a episode to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_episode")
        .in(PathInputs.pathTVShowId)
        .in(PathInputs.pathSeasonNumber)
        .in(PathInputs.pathEpisodeNumber)
        .out(MediaOutputs.listOfAllMediaIds)

  val addOnHoldVideogame:
    PublicEndpoint[(UserId, VideogameId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' videogame endpoint",
        "This endpoint adds a videogame to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_videogame")
        .in(PathInputs.pathVideogameId)
        .out(MediaOutputs.listOfAllMediaIds)

  val addOnHoldBook:
    PublicEndpoint[(UserId, BookId), UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId], Any] =
      userOnHoldBaseEndpoint(
        "Add 'On Hold' book endpoint",
        "This endpoint adds a book to the list of all the 'On Hold' media content for a user",
        "PUT"
      )
        .in("add_book")
        .in(PathInputs.pathBookId)
        .out(MediaOutputs.listOfAllMediaIds)

  val deleteOnHoldMovie: PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userOnHoldBaseEndpoint(
      "Delete 'On Hold' movie endpoint",
      "This endpoint deletes a movie to the list of all the 'On Hold' media content for a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

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

  val deleteOnHoldEpisode: PublicEndpoint[(UserId, TVShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
    userOnHoldBaseEndpoint(
      "Delete 'On Hold' episode endpoint",
      "This endpoint deletes a episode to the list of all the 'On Hold' media content for a user",
      "DELETE"
    )
      .in("delete_episode")
      .in(PathInputs.pathTVShowId)
      .in(PathInputs.pathSeasonNumber)
      .in(PathInputs.pathEpisodeNumber)

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

  //  val userOnHoldTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' TV shows endpoint",
//      "This endpoint returns a list of all the 'On Hold' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userOnHoldSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' TV seasons endpoint",
//      "This endpoint returns a list of all the 'On Hold' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userOnHoldVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' videogames endpoint",
//      "This endpoint returns a list of all the 'On Hold' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userOnHoldBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' books endpoint",
//      "This endpoint returns a list of all the 'On Hold' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
