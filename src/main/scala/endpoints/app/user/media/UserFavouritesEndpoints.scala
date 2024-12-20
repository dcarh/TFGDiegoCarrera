package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.UserFavourites
import modelClasses.ids.User.UserId

object UserFavouritesEndpoints {

  val userFavouritesEndpoint: PublicEndpoint[UserId, UserError, UserFavourites, Any] =
    userBaseEndpoint(
      "User's favourites endpoint",
      "This endpoint returns the favourite media content (one movie, one TV show, one videogame, one book) for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("favourites")
      .out(MediaOutputs.favouritesSuccess)

  val userFavouriteMovieEndpoint: PublicEndpoint[UserId, UserError, Movie, Any] =
    userBaseEndpoint(
      "User's favourite movie endpoint",
      "This endpoint returns the favourite movie for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("favourites" / "movie")
      .out(MediaOutputs.movieSuccess)

  val userFavouriteTVShowEndpoint: PublicEndpoint[UserId, UserError, TVShow, Any] =
    userBaseEndpoint(
      "User's favourite TV show endpoint",
      "This endpoint returns the favourite TV show for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("favourites" / "tv_show")
      .out(MediaOutputs.tvShowSuccess)

  val userFavouriteVideogameEndpoint: PublicEndpoint[UserId, UserError, Videogame, Any] =
    userBaseEndpoint(
      "User's favourite videogame endpoint",
      "This endpoint returns the favourite videogame for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("favourites" / "videogame")
      .out(MediaOutputs.videogameSuccess)

  val userFavouriteBookEndpoint: PublicEndpoint[UserId, UserError, Book, Any] =
    userBaseEndpoint(
      "User's favourite book endpoint",
      "This endpoint returns the favourite book for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("favourites" / "book")
      .out(MediaOutputs.bookSuccess)

}
