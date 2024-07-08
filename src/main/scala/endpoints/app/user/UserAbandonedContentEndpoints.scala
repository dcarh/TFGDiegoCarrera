package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList

object UserAbandonedContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  val userAbandonedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' media content endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' media content for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("abandoned")
      .out(SocialOutputs.jsonMediaContentListOut)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' movies endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' movies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("abandoned" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV shows for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("abandoned" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV seasons for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("abandoned" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' videogames endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' videogames for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("abandoned" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userAbandonedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' books endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' books for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("abandoned" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
