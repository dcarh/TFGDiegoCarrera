package api.user

import sttp.tapir._

import modelClasses.media.{Movie, TVShow, Videogame, Book}
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserFavouritesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userFavouritesEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's favourite elements endpoint")
      .description("This endpoint returns the favourite elements (one movie, one TV show, one videogame, one book) for a user")
      .get
      .in(inputs.pathUsername)
      .in("favourites")
      .out(outputs.jsonMediaContentListOut)

  val userFavouriteMovieEndpoint: PublicEndpoint[String, Unit, Movie, Any] =
    userBaseEndpoint
      .name("User's favourite movie endpoint")
      .description("This endpoint returns the favourite movie for a user")
      .get
      .in(inputs.pathUsername)
      .in("favourites" / "movie")
      .out(outputs.jsonMovieOut)

  val userFavouriteTVShowEndpoint: PublicEndpoint[String, Unit, TVShow, Any] =
    userBaseEndpoint
      .name("User's favourite TV show endpoint")
      .description("This endpoint returns the favourite TV show for a user")
      .get
      .in(inputs.pathUsername)
      .in("favourites" / "tv_show")
      .out(outputs.jsonTVShowOut)

  val userFavouriteVideogameEndpoint: PublicEndpoint[String, Unit, Videogame, Any] =
    userBaseEndpoint
      .name("User's favourite videogame endpoint")
      .description("This endpoint returns the favourite videogame for a user")
      .get
      .in(inputs.pathUsername)
      .in("favourites" / "videogame")
      .out(outputs.jsonVideogameOut)

  val userFavouriteBookEndpoint: PublicEndpoint[String, Unit, Book, Any] =
    userBaseEndpoint
      .name("User's favourite book endpoint")
      .description("This endpoint returns the favourite book for a user")
      .get
      .in(inputs.pathUsername)
      .in("favourites" / "book")
      .out(outputs.jsonBookOut)

}
