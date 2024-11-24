package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Review
import modelClasses.ids.User.UserId

object UserReviewsEndpoints {
  
  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviews endpoint")
      .description("This endpoint returns a list of all the reviews written by a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews")
      .out(SocialOutputs.jsonReviewListOut)

  val userMoviesReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed movies endpoint")
      .description("This endpoint returns a list of all the reviewed movies for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews" / "movies")
      .out(SocialOutputs.jsonReviewListOut)

  val userTVShowsReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV shows endpoint")
      .description("This endpoint returns a list of all the reviewed TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews" / "tv_shows")
      .out(SocialOutputs.jsonReviewListOut)

  val userSeasonsReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV seasons endpoint")
      .description("This endpoint returns a list of all the reviewed TV seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews" / "seasons")
      .out(SocialOutputs.jsonReviewListOut)

  val userEpisodesReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed TV episodes endpoint")
      .description("This endpoint returns a list of all the reviewed TV episodes for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews" / "episodes")
      .out(SocialOutputs.jsonReviewListOut)

  val userVideogamesReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed videogames endpoint")
      .description("This endpoint returns a list of all the reviewed videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews" / "videogames")
      .out(SocialOutputs.jsonReviewListOut)

  val userBooksReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed books endpoint")
      .description("This endpoint returns a list of all the reviewed books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("reviews" / "books")
      .out(SocialOutputs.jsonReviewListOut)
}
