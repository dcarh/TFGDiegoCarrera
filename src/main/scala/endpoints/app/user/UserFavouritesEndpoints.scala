package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}

object UserFavouritesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userFavouritesEndpoint: PublicEndpoint[String, Unit, List[Movie | TVShow | Videogame | Book], Any] =
    userBaseEndpoint
      .name("User's favourites endpoint")
      .description("This endpoint returns the favourite media content (one movie, one TV show, one videogame, one book) for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("favourites")
      .out(MediaOutputs.jsonFavouritesOut)

  val userFavouriteMovieEndpoint: PublicEndpoint[String, Unit, Movie, Any] =
    userBaseEndpoint
      .name("User's favourite movie endpoint")
      .description("This endpoint returns the favourite movie for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("favourites" / "movie")
      .out(MediaOutputs.jsonMovieOut)

  val userFavouriteTVShowEndpoint: PublicEndpoint[String, Unit, TVShow, Any] =
    userBaseEndpoint
      .name("User's favourite TV show endpoint")
      .description("This endpoint returns the favourite TV show for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("favourites" / "tv_show")
      .out(MediaOutputs.jsonTVShowOut)

  val userFavouriteVideogameEndpoint: PublicEndpoint[String, Unit, Videogame, Any] =
    userBaseEndpoint
      .name("User's favourite videogame endpoint")
      .description("This endpoint returns the favourite videogame for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("favourites" / "videogame")
      .out(MediaOutputs.jsonVideogameOut)

  val userFavouriteBookEndpoint: PublicEndpoint[String, Unit, Book, Any] =
    userBaseEndpoint
      .name("User's favourite book endpoint")
      .description("This endpoint returns the favourite book for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("favourites" / "book")
      .out(MediaOutputs.jsonBookOut)

}
