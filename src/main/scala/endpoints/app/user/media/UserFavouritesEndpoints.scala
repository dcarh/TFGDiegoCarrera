package endpoints.app.user.media

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.UserFavourites
import modelClasses.ids.Media.{BookId, MovieId, TvShowId, VideogameId}
import modelClasses.ids.User.UserId

object UserFavouritesEndpoints {

  private val userFavouritesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("favourites")

  val getFavourites: PublicEndpoint[UserId, UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "User's favourites endpoint",
      "This endpoint returns the favourite media content (one movie, one TV show, one videogame, one book) for a user",
      "GET"
    )
      .out(MediaOutputs.favouritesOutput)

  val addFavouriteMovie: PublicEndpoint[(UserId, MovieId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' movie endpoint",
      "This endpoint adds a movie to the 'Favourites' of a user",
      "PUT"
    )
      .in("add_movie")
      .in(PathInputs.pathMovieId)
      .out(MediaOutputs.favouritesOutput)

  val addFavouriteTvShow: PublicEndpoint[(UserId, TvShowId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' TV show endpoint",
      "This endpoint adds a TV show to the list of all the 'Favourites' of a user",
      "PUT"
    )
      .in("add_tv_show")
      .in(PathInputs.pathTvShowId)
      .out(MediaOutputs.favouritesOutput)

  val addFavouriteVideogame: PublicEndpoint[(UserId, VideogameId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' videogame endpoint",
      "This endpoint adds a videogame to the list of all the 'Favourites' of a user",
      "PUT"
    )
      .in("add_videogame")
      .in(PathInputs.pathVideogameId)
      .out(MediaOutputs.favouritesOutput)

  val addFavouriteBook: PublicEndpoint[(UserId, BookId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' book endpoint",
      "This endpoint adds a book to the list of all the 'Favourites' of a user",
      "PUT"
    )
      .in("add_book")
      .in(PathInputs.pathBookId)
      .out(MediaOutputs.favouritesOutput)

  val deleteFavouriteMovie: PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userFavouritesBaseEndpoint(
      "Delete 'Favourite' movie endpoint",
      "This endpoint deletes a movie from the 'Favourites' of a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteFavouriteTvShow: PublicEndpoint[(UserId, TvShowId), UserError, Unit, Any] =
    userFavouritesBaseEndpoint(
      "Delete 'Favourite' TV show endpoint",
      "This endpoint deletes a TV show from the 'Favourites' of a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTvShowId)

  val deleteFavouriteVideogame: PublicEndpoint[(UserId, VideogameId), UserError, Unit, Any] =
    userFavouritesBaseEndpoint(
      "Delete 'Favourite' videogame endpoint",
      "This endpoint deletes a videogame from the 'Favourites' of a user",
      "DELETE"
    )
      .in("delete_videogame")
      .in(PathInputs.pathVideogameId)

  val deleteFavouriteBook: PublicEndpoint[(UserId, BookId), UserError, Unit, Any] =
    userFavouritesBaseEndpoint(
      "Delete 'Favourite' book endpoint",
      "This endpoint deletes a book from the 'Favourites' of a user",
      "DELETE"
    )
      .in("delete_book")
      .in(PathInputs.pathBookId)

}
