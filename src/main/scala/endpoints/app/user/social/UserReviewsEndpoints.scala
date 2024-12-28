package endpoints.app.user

import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Review
import modelClasses.ids.User.UserId

object UserReviewsEndpoints {

  private val userReviewsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => userBaseEndpoint(name, description, method)
      .in(PathInputs.pathUserId)
      .in("reviews")

  val getUserReviewsList: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userReviewsBaseEndpoint(
      "User's reviews endpoint",
      "This endpoint returns a list of all the reviews written by a user",
      "GET"
    )
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userMoviesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userReviewsBaseEndpoint(
//      "User's movie reviews endpoint",
//      "This endpoint returns a list of all a user's movie reviews",
//      "GET"
//    )
//      .in("movies")
//      .out(SocialOutputs.listOfReviewsSuccess)
//
//  val userTVShowsReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userReviewsBaseEndpoint(
//      "User's TV show reviews endpoint",
//      "This endpoint returns a list of all a user's TV show reviews",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(SocialOutputs.listOfReviewsSuccess)
//
//  val userSeasonsReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userReviewsBaseEndpoint(
//      "User's season reviews endpoint",
//      "This endpoint returns a list of all a user's season reviews",
//      "GET"
//    )
//      .in("seasons")
//      .out(SocialOutputs.listOfReviewsSuccess)
//
//  val userEpisodesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userReviewsBaseEndpoint(
//      "User's episode reviews endpoint",
//      "This endpoint returns a list of all a user's episode reviews",
//      "GET"
//    )
//      .in("episodes")
//      .out(SocialOutputs.listOfReviewsSuccess)
//
//  val userVideogamesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userReviewsBaseEndpoint(
//      "User's videogame reviews endpoint",
//      "This endpoint returns a list of all a user's videogame reviews",
//      "GET"
//    )
//      .in("videogames")
//      .out(SocialOutputs.listOfReviewsSuccess)
//
//  val userBooksReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userReviewsBaseEndpoint(
//      "User's books reviews endpoint",
//      "This endpoint returns a list of all a user's book reviews",
//      "GET"
//    )
//      .in("books")
//      .out(SocialOutputs.listOfReviewsSuccess)
}
