package endpoints.app.user

import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Review
import modelClasses.ids.User.UserId

object UserReviewsEndpoints {

  val userReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's reviews endpoint",
      "This endpoint returns a list of all the reviews written by a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews")
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userMoviesReviewsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie], Any] =
//    userBaseEndpoint(
//      "User's reviewed movies endpoint",
//      "This endpoint returns a list of all the reviewed movies for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("reviews" / "movies")
//      .out(SocialOutputs.listOfMoviesSuccess)

  val userMoviesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's movie reviews endpoint",
      "This endpoint returns a list of all a user's movie reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews" / "movies")
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userTVShowsReviewsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow], Any] =
//    userBaseEndpoint(
//      "User's reviewed TV shows endpoint",
//      "This endpoint returns a list of all the reviewed TV shows for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("reviews" / "tv_shows")
//      .out(SocialOutputs.listOfTvShowsSuccess)

  val userTVShowsReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's TV show reviews endpoint",
      "This endpoint returns a list of all a user's TV show reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews" / "tv_shows")
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userSeasonsReviewsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Season], Any] =
//    userBaseEndpoint(
//      "User's reviewed TV seasons endpoint",
//      "This endpoint returns a list of all the reviewed TV seasons for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("reviews" / "seasons")
//      .out(SocialOutputs.listOfSeasonsSuccess)

  val userSeasonsReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's season reviews endpoint",
      "This endpoint returns a list of all a user's season reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews" / "seasons")
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userEpisodesReviewsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Episode], Any] =
//    userBaseEndpoint(
//      "User's reviewed TV episodes endpoint",
//      "This endpoint returns a list of all the reviewed TV episodes for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("reviews" / "episodes")
//      .out(SocialOutputs.listOfEpisodesSuccess)

  val userEpisodesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's episode reviews endpoint",
      "This endpoint returns a list of all a user's episode reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews" / "episodes")
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userVideogamesReviewsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Videogames], Any] =
//    userBaseEndpoint(
//      "User's reviewed videogames endpoint",
//      "This endpoint returns a list of all the reviewed videogames for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("reviews" / "videogames")
//      .out(SocialOutputs.listOfVideogamesSuccess)

  val userVideogamesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's videogame reviews endpoint",
      "This endpoint returns a list of all a user's videogame reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews" / "videogames")
      .out(SocialOutputs.listOfReviewsSuccess)

//  val userBooksReviewsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Book], Any] =
//    userBaseEndpoint(
//      "User's reviewed books endpoint",
//      "This endpoint returns a list of all the reviewed books for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("reviews" / "books")
//      .out(SocialOutputs.listOfBookSuccess)

  val userBooksReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's books reviews endpoint",
      "This endpoint returns a list of all a user's book reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("reviews" / "books")
      .out(SocialOutputs.listOfReviewsSuccess)
}
