package api.user

import sttp.tapir._
import modelClasses.media._
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserAbandonedContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userAbandonedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' elements endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' elements for a user")
      .get
      .in(inputs.pathUsername)
      .in("abandoned")
      .out(outputs.jsonMediaContentListOut)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' movies endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("abandoned" / "movies")
      .out(outputs.jsonMovieListOut)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("abandoned" / "tv_shows")
      .out(outputs.jsonTVShowListOut)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("abandoned" / "seasons")
      .out(outputs.jsonSeasonListOut)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' videogames endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("abandoned" / "videogames")
      .out(outputs.jsonVideogameListOut)

  val userAbandonedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' books endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' books for a user")
      .get
      .in(inputs.pathUsername)
      .in("abandoned" / "books")
      .out(outputs.jsonBookListOut)

}
