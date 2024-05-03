package api.user

import sttp.tapir._

import modelClasses.media._
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserCompletedContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userCompletedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Completed' endpoint")
      .description("This endpoint returns a list of all the 'Completed' elements for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed")
      .out(outputs.jsonMediaContentListOut)

  val userCompletedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Completed' movies endpoint")
      .description("This endpoint returns a list of all the 'Completed' movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed" / "movies")
      .out(outputs.jsonMovieListOut)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Completed' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Completed' TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed" / "tv_shows")
      .out(outputs.jsonTVShowListOut)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Completed' seasons endpoint")
      .description("This endpoint returns a list of all the 'Completed' seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed" / "seasons")
      .out(outputs.jsonSeasonListOut)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's 'Completed' episodes endpoint")
      .description("This endpoint returns a list of all the 'Completed' episodes for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed" / "episodes")
      .out(outputs.jsonEpisodeListOut)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Completed' videogames endpoint")
      .description("This endpoint returns a list of all the 'Completed' videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed" / "videogames")
      .out(outputs.jsonVideogameListOut)

  val userCompletedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Completed' books endpoint")
      .description("This endpoint returns a list of all the 'Completed' books for a user")
      .get
      .in(inputs.pathUsername)
      .in("completed" / "books")
      .out(outputs.jsonBookListOut)

}
