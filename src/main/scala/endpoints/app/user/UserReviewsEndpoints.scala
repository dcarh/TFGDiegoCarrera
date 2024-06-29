package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.social.Review

object UserReviewsEndpoints {
  
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
      .in(PathInputs.pathUsername)
      .in("reviews")
      .out(SocialOutputs.jsonReviewListOut)

  val userMoviesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed movies endpoint")
      .description("This endpoint returns a list of all the reviewed movies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("reviews" / "movies")
      .out(SocialOutputs.jsonReviewListOut)

  val userTVShowsReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV shows endpoint")
      .description("This endpoint returns a list of all the reviewed TV shows for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("reviews" / "tv_shows")
      .out(SocialOutputs.jsonReviewListOut)

  val userSeasonsReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV seasons endpoint")
      .description("This endpoint returns a list of all the reviewed TV seasons for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("reviews" / "seasons")
      .out(SocialOutputs.jsonReviewListOut)

  val userEpisodesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV episodes endpoint")
      .description("This endpoint returns a list of all the reviewed TV episodes for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("reviews" / "episodes")
      .out(SocialOutputs.jsonReviewListOut)

  val userVideogamesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed videogames endpoint")
      .description("This endpoint returns a list of all the reviewed videogames for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("reviews" / "videogames")
      .out(SocialOutputs.jsonReviewListOut)

  val userBooksReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed books endpoint")
      .description("This endpoint returns a list of all the reviewed books for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("reviews" / "books")
      .out(SocialOutputs.jsonReviewListOut)
}
