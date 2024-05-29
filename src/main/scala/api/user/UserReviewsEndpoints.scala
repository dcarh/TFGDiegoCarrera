package api.user

import sttp.tapir._

import modelClasses.social.Review
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserReviewsEndpoints {
  
  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviews endpoint")
      .description("This endpoint returns a list of all the reviews written by a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews")
      .out(outputs.jsonReviewListOut)

  val userMoviesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed movies endpoint")
      .description("This endpoint returns a list of all the reviewed movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews" / "movies")
      .out(outputs.jsonReviewListOut)

  val userTVShowsReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV shows endpoint")
      .description("This endpoint returns a list of all the reviewed TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews" / "tv_shows")
      .out(outputs.jsonReviewListOut)

  val userSeasonsReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV seasons endpoint")
      .description("This endpoint returns a list of all the reviewed TV seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews" / "seasons")
      .out(outputs.jsonReviewListOut)

  val userEpisodesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV episodes endpoint")
      .description("This endpoint returns a list of all the reviewed TV episodes for a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews" / "episodes")
      .out(outputs.jsonReviewListOut)

  val userVideogamesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed videogames endpoint")
      .description("This endpoint returns a list of all the reviewed videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews" / "videogames")
      .out(outputs.jsonReviewListOut)

  val userBooksReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed books endpoint")
      .description("This endpoint returns a list of all the reviewed books for a user")
      .get
      .in(inputs.pathUsername)
      .in("reviews" / "books")
      .out(outputs.jsonReviewListOut)

  //val userReviewedContentListEndpoint: PublicEndpoint[String, Unit, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed contents endpoint")
  //    .description("This endpoint returns a list of all the reviewed contents for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "all")
  //    .out(outputs.jsonMediaListOut)
//
  //val userReviewsMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed movies endpoint")
  //    .description("This endpoint returns a list of all the reviewed movies for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "movies")
  //    .out(outputs.jsonMovieListOut)
//
  //val userReviewsTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed TV shows endpoint")
  //    .description("This endpoint returns a list of all the reviewed TV shows for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "tv_shows")
  //    .out(outputs.jsonTVShowListOut)
//
  //val userReviewsSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed TV seasons endpoint")
  //    .description("This endpoint returns a list of all the reviewed TV seasons for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "seasons")
  //    .out(outputs.jsonSeasonListOut)
//
  //val userReviewsEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed TV episodes endpoint")
  //    .description("This endpoint returns a list of all the reviewed TV episodes for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "episodes")
  //    .out(outputs.jsonEpisodeListOut)
//
  //val userReviewsVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed videogames endpoint")
  //    .description("This endpoint returns a list of all the reviewed videogames for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "videogames")
  //    .out(outputs.jsonVideogameListOut)
//
  //val userReviewsBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
  //  userBaseEndpoint
  //    .name("User's reviewed books endpoint")
  //    .description("This endpoint returns a list of all the reviewed books for a user")
  //    .get
  //    .in(inputs.pathUsername)
  //    .in("reviews" / "books")
  //    .out(outputs.jsonBookListOut)
}
