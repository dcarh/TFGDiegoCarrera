package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.UserFavourites
import modelClasses.ids.Media.{BookId, MovieId, TVShowId, VideogameId}
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
      .out(MediaOutputs.favouritesSuccess)

  val addFavouriteMovie: PublicEndpoint[(UserId, MovieId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' movie endpoint",
      "This endpoint adds a movie to the 'Favourites' of a user",
      "PUT"
    )
      .in("add_movie")
      .in(PathInputs.pathMovieId)
      .out(MediaOutputs.favouritesSuccess)

  val addFavouriteTvShow: PublicEndpoint[(UserId, TVShowId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' TV show endpoint",
      "This endpoint adds a TV show to the list of all the 'Favourites' of a user",
      "PUT"
    )
      .in("add_tv_show")
      .in(PathInputs.pathTVShowId)
      .out(MediaOutputs.favouritesSuccess)

  val addFavouriteVideogame: PublicEndpoint[(UserId, VideogameId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' videogame endpoint",
      "This endpoint adds a videogame to the list of all the 'Favourites' of a user",
      "PUT"
    )
      .in("add_videogame")
      .in(PathInputs.pathVideogameId)
      .out(MediaOutputs.favouritesSuccess)

  val addFavouriteBook: PublicEndpoint[(UserId, BookId), UserError, UserFavourites, Any] =
    userFavouritesBaseEndpoint(
      "Add 'Favourite' book endpoint",
      "This endpoint adds a book to the list of all the 'Favourites' of a user",
      "PUT"
    )
      .in("add_book")
      .in(PathInputs.pathBookId)
      .out(MediaOutputs.favouritesSuccess)

  val deleteFavouriteMovie: PublicEndpoint[(UserId, MovieId), UserError, Unit, Any] =
    userFavouritesBaseEndpoint(
      "Delete 'Favourite' movie endpoint",
      "This endpoint deletes a movie from the 'Favourites' of a user",
      "DELETE"
    )
      .in("delete_movie")
      .in(PathInputs.pathMovieId)

  val deleteFavouriteTvShow: PublicEndpoint[(UserId, TVShowId), UserError, Unit, Any] =
    userFavouritesBaseEndpoint(
      "Delete 'Favourite' TV show endpoint",
      "This endpoint deletes a TV show from the 'Favourites' of a user",
      "DELETE"
    )
      .in("delete_tv_show")
      .in(PathInputs.pathTVShowId)

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

//  val userFavouriteMovieEndpoint: PublicEndpoint[UserId, UserError, Movie, Any] =
//    userFavouritesBaseEndpoint(
//      "User's favourite movie endpoint",
//      "This endpoint returns the favourite movie for a user",
//      "GET"
//    )
//      .in("movie")
//      .out(MediaOutputs.movieSuccess)
//
//  val userFavouriteTVShowEndpoint: PublicEndpoint[UserId, UserError, TVShow, Any] =
//    userFavouritesBaseEndpoint(
//      "User's favourite TV show endpoint",
//      "This endpoint returns the favourite TV show for a user",
//      "GET"
//    )
//      .in("tv_show")
//      .out(MediaOutputs.tvShowSuccess)
//
//  val userFavouriteVideogameEndpoint: PublicEndpoint[UserId, UserError, Videogame, Any] =
//    userFavouritesBaseEndpoint(
//      "User's favourite videogame endpoint",
//      "This endpoint returns the favourite videogame for a user",
//      "GET"
//    )
//      .in("videogame")
//      .out(MediaOutputs.videogameSuccess)
//
//  val userFavouriteBookEndpoint: PublicEndpoint[UserId, UserError, Book, Any] =
//    userFavouritesBaseEndpoint(
//      "User's favourite book endpoint",
//      "This endpoint returns the favourite book for a user",
//      "GET"
//    )
//      .in("book")
//      .out(MediaOutputs.bookSuccess)

}
