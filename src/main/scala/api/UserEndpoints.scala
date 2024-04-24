package api

import io.circe.generic.auto._
import modelClasses.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, MediaContentList, Review, Comment,
  UserSettings, ErrorInfo, Chat}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.model.StatusCode

class UserEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathUserId: EndpointInput[User.Id] =
    path[User.Id]("user_id")

  private val pathUsername: EndpointInput[String] =
    path[String]("username")

  private val pathListId: EndpointInput[MediaContentList.Id] =
    path[MediaContentList.Id]("list_id")

  private val queryType: EndpointInput[String] =
    query[String]("type")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by")

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // val userBaseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
  //  endpoint.in("api" / "v1.0").errorOut(jsonBody[ErrorInfo])

  // private val jsonElementOut: EndpointOutput[Element] =
  //   jsonBody[Element]

  private val jsonMovieOut: EndpointOutput[Movie] =
    jsonBody[Movie]

  private val jsonTVShowOut: EndpointOutput[TVShow] =
    jsonBody[TVShow]

  private val jsonSeasonOut: EndpointOutput[Season] =
    jsonBody[Season]

  private val jsonEpisodeOut: EndpointOutput[Episode] =
    jsonBody[Episode]

  private val jsonVideogameOut: EndpointOutput[Videogame] =
    jsonBody[Videogame]

  private val jsonBookOut: EndpointOutput[Book] =
    jsonBody[Book]

  private val jsonReviewOut: EndpointOutput[Review] =
    jsonBody[Review]

  private val jsonCommentOut: EndpointOutput[Comment] =
    jsonBody[Comment]

  private val jsonUserOut: EndpointOutput[User] =
    jsonBody[User]

  private val jsonChatOut: EndpointOutput[Chat] =
    jsonBody[Chat]

  private val jsonElementListOut: EndpointOutput[MediaContentList] =
    jsonBody[MediaContentList]

  private val jsonMovieListOut: EndpointOutput[List[Movie]] =
    jsonBody[List[Movie]]

  private val jsonTVShowListOut: EndpointOutput[List[TVShow]] =
    jsonBody[List[TVShow]]

  private val jsonSeasonListOut: EndpointOutput[List[Season]] =
    jsonBody[List[Season]]

  private val jsonEpisodeListOut: EndpointOutput[List[Episode]] =
    jsonBody[List[Episode]]

  private val jsonVideogameListOut: EndpointOutput[List[Videogame]] =
    jsonBody[List[Videogame]]

  private val jsonBookListOut: EndpointOutput[List[Book]] =
    jsonBody[List[Book]]

  private val jsonReviewListOut: EndpointOutput[List[Review]] =
    jsonBody[List[Review]]

  private val jsonCommentListOut: EndpointOutput[List[Comment]] =
    jsonBody[List[Comment]]

  private val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  private val jsonChatListOut: EndpointOutput[List[Chat]] =
    jsonBody[List[Chat]]
  
  private val jsonListOfElementListOut: EndpointOutput[List[MediaContentList]] =
    jsonBody[List[MediaContentList]]

  private val jsonSettingsOut: EndpointOutput[UserSettings] =
    jsonBody[UserSettings]

  private val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
    jsonBody[ErrorInfo]

  private val jsonSettingsIn: EndpointInput[UserSettings] =
    jsonBody[UserSettings]

  private val jsonElementListIn: EndpointInput[MediaContentList] =
    jsonBody[MediaContentList]

  val testEndpoint: PublicEndpoint[String, Unit, List[User], Any] =
    endpoint.get
      .in("api" / "user" / "test")
      .in(queryType)
      .out(jsonUserListOut)

  // TODO: ¿Input?
  val userSignUpEndpoint: PublicEndpoint[Unit, Unit, User, Any] =
    userBaseEndpoint
      .name("Sign up endpoint")
      .description("With this endpoint, a person can sign up in the app. The endpoint returns the created user in case of success")
      .post
      .in("sign-up")
      .out(jsonUserOut)

  // TODO: ¿Input?
  val userSignInEndpoint: PublicEndpoint[Unit, Unit, User, Any] =
    userBaseEndpoint
      .name("Sign in endpoint")
      .description("With this endpoint, a person can sign in in the app. The endpoint returns the user in case of success")
      .post
      .in("sign-in")
      .out(jsonUserOut)

  // Endpoint that returns a list with all the users in the app
  val usersEndpoint: PublicEndpoint[String, Unit, List[User], Any] =
    usersBaseEndpoint
      .name("Users endpoint")
      .description("This endpoint returns a list of all the users in the app")
      .get
      .in(queryOrderBy)
      .out(jsonUserListOut)
  

  // Endpoints for elements of "Completed" category
  val userCompletedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Completed' endpoint")
      .description("This endpoint returns a list of all the 'Completed' elements for a user")
      .get
      .in(pathUsername)
      .in("completed")
      .out(jsonElementListOut)

  val userCompletedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Completed' movies endpoint")
      .description("This endpoint returns a list of all the 'Completed' movies for a user")
      .get
      .in(pathUsername)
      .in("completed" / "movies")
      .out(jsonMovieListOut)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Completed' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Completed' TV shows for a user")
      .get
      .in(pathUsername)
      .in("completed" / "tv_shows")
      .out(jsonTVShowListOut)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Completed' seasons endpoint")
      .description("This endpoint returns a list of all the 'Completed' seasons for a user")
      .get
      .in(pathUsername)
      .in("completed" / "seasons")
      .out(jsonSeasonListOut)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's 'Completed' episodes endpoint")
      .description("This endpoint returns a list of all the 'Completed' episodes for a user")
      .get
      .in(pathUsername)
      .in("completed" / "episodes")
      .out(jsonEpisodeListOut)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Completed' videogames endpoint")
      .description("This endpoint returns a list of all the 'Completed' videogames for a user")
      .get
      .in(pathUsername)
      .in("completed" / "videogames")
      .out(jsonVideogameListOut)

  val userCompletedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Completed' books endpoint")
      .description("This endpoint returns a list of all the 'Completed' books for a user")
      .get
      .in(pathUsername)
      .in("completed" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "In Progress" category
  val userInProgressListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'In Progress' elements endpoint")
      .description("This endpoint returns a list of all the 'In Progress' elements for a user")
      .get
      .in(pathUsername)
      .in("in_progress")
      .out(jsonElementListOut)

  val userInProgressMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' movies endpoint")
      .description("This endpoint returns a list of all the 'In Progress' movies for a user")
      .get
      .in(pathUsername)
      .in("in_progress" / "movies")
      .out(jsonMovieListOut)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV shows endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV shows for a user")
      .get
      .in(pathUsername)
      .in("in_progress" / "tv_shows")
      .out(jsonTVShowListOut)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV seasons for a user")
      .get
      .in(pathUsername)
      .in("in_progress" / "seasons")
      .out(jsonSeasonListOut)

  val userInProgressEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's 'In Progress' TV episodes endpoint")
    .description("This endpoint returns a list of all the 'In Progress' TV episodes for a user")
    .get
    .in(pathUsername)
    .in("in_progress" / "episodes")
    .out(jsonEpisodeListOut)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' videogames endpoint")
      .description("This endpoint returns a list of all the 'In Progress' videogames for a user")
      .get
      .in(pathUsername)
      .in("in_progress" / "videogames")
      .out(jsonVideogameListOut)

  val userInProgressBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' books endpoint")
      .description("This endpoint returns a list of all the 'In Progress' books for a user")
      .get
      .in(pathUsername)
      .in("in_progress" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Pending" category
  val userPendingListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Pending' elements endpoint")
      .description("This endpoint returns a list of all the 'Pending' elements for a user")
      .get
      .in(pathUsername)
      .in("pending")
      .out(jsonElementListOut)

  val userPendingMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Pending' movies endpoint")
      .description("This endpoint returns a list of all the 'Pending' movies for a user")
      .get
      .in(pathUsername)
      .in("pending" / "movies")
      .out(jsonMovieListOut)

  val userPendingTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV shows for a user")
      .get
      .in(pathUsername)
      .in("pending" / "tv_shows")
      .out(jsonTVShowListOut)

  val userPendingSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV seasons for a user")
      .get
      .in(pathUsername)
      .in("pending" / "seasons")
      .out(jsonSeasonListOut)

  val userPendingEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's 'Pending' TV episodes endpoint")
    .description("This endpoint returns a list of all the 'Pending' TV episodes for a user")
    .get
    .in(pathUsername)
    .in("pending" / "episodes")
    .out(jsonEpisodeListOut)

  val userPendingVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Pending' videogames endpoint")
      .description("This endpoint returns a list of all the 'Pending' videogames for a user")
      .get
      .in(pathUsername)
      .in("pending" / "videogames")
      .out(jsonVideogameListOut)

  val userPendingBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Pending' books endpoint")
      .description("This endpoint returns a list of all the 'Pending' books for a user")
      .get
      .in(pathUsername)
      .in("pending" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "On Hold" category
  val userOnHoldListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'On Hold' elements endpoint")
      .description("This endpoint returns a list of all the 'On Hold' elements for a user")
      .get
      .in(pathUsername)
      .in("on_hold")
      .out(jsonElementListOut)

  val userOnHoldMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' movies endpoint")
      .description("This endpoint returns a list of all the 'On Hold' movies for a user")
      .get
      .in(pathUsername)
      .in("on_hold" / "movies")
      .out(jsonMovieListOut)

  val userOnHoldTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' TV shows endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV shows for a user")
      .get
      .in(pathUsername)
      .in("on_hold" / "tv_shows")
      .out(jsonTVShowListOut)

  val userOnHoldSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV seasons for a user")
      .get
      .in(pathUsername)
      .in("on_hold" / "seasons")
      .out(jsonSeasonListOut)

  val userOnHoldEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's 'On Hold' TV episodes endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV episodes for a user")
      .get
    .in(pathUsername)
    .in("on_hold" / "episodes")
    .out(jsonEpisodeListOut)

  val userOnHoldVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' videogames endpoint")
      .description("This endpoint returns a list of all the 'On Hold' videogames for a user")
      .get
      .in(pathUsername)
      .in("on_hold" / "videogames")
      .out(jsonVideogameListOut)

  val userOnHoldBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' books endpoint")
      .description("This endpoint returns a list of all the 'On Hold' books for a user")
      .get
      .in(pathUsername)
      .in("on_hold" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Abandoned" category
  val userAbandonedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' elements endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' elements for a user")
      .get
      .in(pathUsername)
      .in("abandoned")
      .out(jsonElementListOut)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' movies endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' movies for a user")
      .get
      .in(pathUsername)
      .in("abandoned" / "movies")
      .out(jsonMovieListOut)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV shows for a user")
      .get
      .in(pathUsername)
      .in("abandoned" / "tv_shows")
      .out(jsonTVShowListOut)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV seasons for a user")
      .get
      .in(pathUsername)
      .in("abandoned" / "seasons")
      .out(jsonSeasonListOut)

  val userAbandonedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's 'Abandoned' TV episodes endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV episodes for a user")
      .get
    .in(pathUsername)
    .in("abandoned" / "episodes")
    .out(jsonEpisodeListOut)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' videogames endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' videogames for a user")
      .get
      .in(pathUsername)
      .in("abandoned" / "videogames")
      .out(jsonVideogameListOut)

  val userAbandonedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' books endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' books for a user")
      .get
      .in(pathUsername)
      .in("abandoned" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Wishlist" category
  val userWishlistListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Wishlist' elements endpoint")
      .description("This endpoint returns a list of all the 'Wishlist' elements for a user")
      .get
      .in(pathUsername)
      .in("wishlist")
      .out(jsonElementListOut)

  val userWishlistMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Wishlist' movies endpoint")
      .description("This endpoint returns a list of all the 'Wishlist' movies for a user")
      .get
      .in(pathUsername)
      .in("wishlist" / "movies")
      .out(jsonMovieListOut)

  val userWishlistTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Wishlist' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Wishlist' TV shows for a user")
      .get
      .in(pathUsername)
      .in("wishlist" / "tv_shows")
      .out(jsonTVShowListOut)

  val userWishlistSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Wishlist' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Wishlist' TV seasons for a user")
      .get
      .in(pathUsername)
      .in("wishlist" / "seasons")
      .out(jsonSeasonListOut)

  val userWishlistEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's 'Wishlist' TV episodes endpoint")
    .description("This endpoint returns a list of all the 'Wishlist' TV episodes for a user")
    .get
    .in(pathUsername)
    .in("wishlist" / "episodes")
    .out(jsonEpisodeListOut)

  val userWishlistVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Wishlist' videogames endpoint")
      .description("This endpoint returns a list of all the 'Wishlist' videogames for a user")
      .get
      .in(pathUsername)
      .in("wishlist" / "videogames")
      .out(jsonVideogameListOut)

  val userWishlistBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Wishlist' books endpoint")
      .description("This endpoint returns a list of all the 'Wishlist' books for a user")
      .get
      .in(pathUsername)
      .in("wishlist" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Owned" category
  val userOwnedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Owned' elements endpoint")
      .description("This endpoint returns a list of all the 'Owned' elements for a user")
      .get
      .in(pathUsername)
      .in("owned")
      .out(jsonElementListOut)

  val userOwnedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Owned' movies endpoint")
      .description("This endpoint returns a list of all the 'Owned' movies for a user")
      .get
      .in(pathUsername)
      .in("owned" / "movies")
      .out(jsonMovieListOut)

  val userOwnedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Owned' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Owned' TV shows for a user")
      .get
      .in(pathUsername)
      .in("owned" / "tv_shows")
      .out(jsonTVShowListOut)

  val userOwnedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Owned' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Owned' TV seasons for a user")
      .get
      .in(pathUsername)
      .in("owned" / "seasons")
      .out(jsonSeasonListOut)

  val userOwnedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's 'Owned' TV episodes endpoint")
    .description("This endpoint returns a list of all the 'Owned' TV episodes for a user")
    .get
    .in(pathUsername)
    .in("owned" / "episodes")
    .out(jsonEpisodeListOut)

  val userOwnedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Owned' videogames endpoint")
      .description("This endpoint returns a list of all the 'Owned' videogames for a user")
      .get
      .in(pathUsername)
      .in("owned" / "videogames")
      .out(jsonVideogameListOut)

  val userOwnedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Owned' books endpoint")
      .description("This endpoint returns a list of all the 'Owned' books for a user")
      .get
      .in(pathUsername)
      .in("owned" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Liked" category
  val userLikesListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's liked elements endpoint")
      .description("This endpoint returns a list of all the liked elements for a user")
      .get
      .in(pathUsername)
      .in("likes")
      .out(jsonElementListOut)

  val userLikedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's liked movies endpoint")
      .description("This endpoint returns a list of all the liked movies for a user")
      .get
      .in(pathUsername)
      .in("likes" / "movies")
      .out(jsonMovieListOut)

  val userLikedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's liked TV shows endpoint")
      .description("This endpoint returns a list of all the liked TV shows for a user")
      .get
      .in(pathUsername)
      .in("likes" / "tv_shows")
      .out(jsonTVShowListOut)

  val userLikedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons endpoint")
      .description("This endpoint returns a list of all the liked TV seasons for a user")
      .get
      .in(pathUsername)
      .in("likes" / "seasons")
      .out(jsonSeasonListOut)

  val userLikedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's liked TV episodes endpoint")
    .description("This endpoint returns a list of all the liked TV episodes for a user")
    .get
    .in(pathUsername)
    .in("likes" / "episodes")
    .out(jsonEpisodeListOut)

  val userLikedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's liked videogames endpoint")
      .description("This endpoint returns a list of all the liked videogames for a user")
      .get
      .in(pathUsername)
      .in("likes" / "videogames")
      .out(jsonVideogameListOut)

  val userLikedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's liked books endpoint")
      .description("This endpoint returns a list of all the liked books for a user")
      .get
      .in(pathUsername)
      .in("likes" / "books")
      .out(jsonBookListOut)

  val userLikedReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's liked reviews endpoint")
      .description("This endpoint returns a list of all the liked reviews for a user")
      .get
      .in(pathUsername)
      .in("likes" / "reviews")
      .out(jsonReviewListOut)

  val userLikedCommentsListEndpoint: PublicEndpoint[String, Unit, List[Comment], Any] =
    userBaseEndpoint
      .name("User's liked comments endpoint")
      .description("This endpoint returns a list of all the liked comments for a user")
      .get
      .in(pathUsername)
      .in("likes" / "comments")
      .out(jsonCommentListOut)

  val userLikedListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked lists endpoint")
      .description("This endpoint returns a list of all the liked lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists")
      .out(jsonListOfElementListOut)

  val userLikedMovieListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked movie lists endpoint")
      .description("This endpoint returns a list of all the liked movie lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists" / "movies")
      .out(jsonListOfElementListOut)

  val userLikedTVShowsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV shows lists endpoint")
      .description("This endpoint returns a list of all the liked TV shows lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists" / "tv_shows")
      .out(jsonListOfElementListOut)

  val userLikedSeasonsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons lists endpoint")
      .description("This endpoint returns a list of all the liked TV seasons lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists" / "seasons")
      .out(jsonListOfElementListOut)

  val userLikedEpisodesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV episodes lists endpoint")
      .description("This endpoint returns a list of all the liked TV episodes lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists" / "episodes")
      .out(jsonListOfElementListOut)

  val userLikedVideogamesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked videogames lists endpoint")
      .description("This endpoint returns a list of all the liked videogames lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists" / "videogames")
      .out(jsonListOfElementListOut)

  val userLikedBooksListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked books lists endpoint")
      .description("This endpoint returns a list of all the liked books lists for a user")
      .get
      .in(pathUsername)
      .in("likes" / "lists" / "books")
      .out(jsonListOfElementListOut)



  // Endpoints for Reviews
  val userReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's reviewed elements endpoint")
      .description("This endpoint returns a list of all the reviewed elements for a user")
      .get
      .in(pathUsername)
      .in("reviews")
      .out(jsonReviewListOut)

  val userReviewsMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's reviewed movies endpoint")
      .description("This endpoint returns a list of all the reviewed movies for a user")
      .get
      .in(pathUsername)
      .in("reviews" / "movies")
      .out(jsonMovieListOut)

  val userReviewsTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's reviewed TV shows endpoint")
      .description("This endpoint returns a list of all the reviewed TV shows for a user")
      .get
      .in(pathUsername)
      .in("reviews" / "tv_shows")
      .out(jsonTVShowListOut)

  val userReviewsSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's reviewed TV seasons endpoint")
      .description("This endpoint returns a list of all the reviewed TV seasons for a user")
      .get
      .in(pathUsername)
      .in("reviews" / "seasons")
      .out(jsonSeasonListOut)

  val userReviewsEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .name("User's reviewed TV episodes endpoint")
    .description("This endpoint returns a list of all the reviewed TV episodes for a user")
    .get
    .in(pathUsername)
    .in("reviews" / "episodes")
    .out(jsonEpisodeListOut)

  val userReviewsVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's reviewed videogames endpoint")
      .description("This endpoint returns a list of all the reviewed videogames for a user")
      .get
      .in(pathUsername)
      .in("reviews" / "videogames")
      .out(jsonVideogameListOut)

  val userReviewsBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's reviewed books endpoint")
      .description("This endpoint returns a list of all the reviewed books for a user")
      .get
      .in(pathUsername)
      .in("reviews" / "books")
      .out(jsonBookListOut)


  // Endpoints for favourite Movies, TV Shows, Seasons, Episodes, Videogames and Books
  // TODO: Las favourites van a ser: 1 película, 1 serie, 1 videojuego y 1 libro
  val userFavouritesEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
  userBaseEndpoint
    .name("User's favourite elements endpoint")
    .description("This endpoint returns the favourite elements (one movie, one TV show, one videogame, one book) for a user")
    .get
    .in(pathUsername)
    .in("favourites")
    .out(jsonElementListOut)

  val userFavouriteMovieEndpoint: PublicEndpoint[String, Unit, Movie, Any] =
    userBaseEndpoint
      .name("User's favourite movie endpoint")
      .description("This endpoint returns the favourite movie for a user")
      .get
      .in(pathUsername)
      .in("favourites" / "movie")
      .out(jsonMovieOut)

  val userFavouriteTVShowEndpoint: PublicEndpoint[String, Unit, TVShow, Any] =
    userBaseEndpoint
      .name("User's favourite TV show endpoint")
      .description("This endpoint returns the favourite TV show for a user")
      .get
      .in(pathUsername)
      .in("favourites" / "tv_show")
      .out(jsonTVShowOut)

  val userFavouriteVideogameEndpoint: PublicEndpoint[String, Unit, Videogame, Any] =
    userBaseEndpoint
      .name("User's favourite videogame endpoint")
      .description("This endpoint returns the favourite videogame for a user")
      .get
      .in(pathUsername)
      .in("favourites" / "videogame")
      .out(jsonVideogameOut)

  val userFavouriteBookEndpoint: PublicEndpoint[String, Unit, Book, Any] =
    userBaseEndpoint
      .name("User's favourite book endpoint")
      .description("This endpoint returns the favourite book for a user")
      .get
      .in(pathUsername)
      .in("favourites" / "book")
      .out(jsonBookOut)

  /*
  val userFavouriteMoviesListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "movies")
      .out(jsonListOfElementListOut)

  val userFavouriteTVShowsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "tv_shows")
      .out(jsonListOfElementListOut)

  val userFavouriteSeasonsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "seasons")
      .out(jsonListOfElementListOut)

  val userFavouriteEpisodesListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "episodes")
      .out(jsonListOfElementListOut)

  val userFavouriteVideogamesListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "videogames")
      .out(jsonListOfElementListOut)

  val userFavouriteBooksListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "books")
      .out(jsonListOfElementListOut)
  */


  // Endpoints for Comments
  val userCommentsListEndpoint: PublicEndpoint[String, Unit, List[Comment], Any] =
  userBaseEndpoint
    .name("User's comments endpoint")
    .description("This endpoint returns all the comments made by a user")
    .get
    .in(pathUsername)
    .in("comments")
    .out(jsonCommentListOut)

  val userCommentsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's comments to lists endpoint")
      .description("This endpoint returns all the comments made by a user specifically to lists")
      .get
      .in(pathUsername)
      .in("comments" / "lists")
      .out(jsonListOfElementListOut)

  val userCommentsReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's comments to reviews endpoint")
      .description("This endpoint returns all the comments made by a user specifically to reviews")
      .get
      .in(pathUsername)
      .in("comments" / "reviews")
      .out(jsonReviewListOut)

  /*
  val userCommentsCommentsListEndpoint: PublicEndpoint[String, Unit, List[Comment], Any] =
    userBaseEndpoint
      .name("User's comments to comments endpoint")
      .description("This endpoint returns all the comments made by a user specifically to other comments")
      .get
      .in(pathUsername)
      .in("comments" / "comments")
      .out(jsonCommentListOut)
   */


  // Endpoints for Settings
  val userSettingsEndpoint: PublicEndpoint[String, Unit, UserSettings, Any] =
    userBaseEndpoint
      .name("User's settings endpoint")
      .description("This endpoint returns the settings [SPECIFY SETTINGS] of a user")
      .get
      .in(pathUsername)
      .in("settings")
      .out(jsonSettingsOut)

  val userEditSettingsEndpoint: PublicEndpoint[(String, UserSettings), Unit, UserSettings, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("settings" / "edit")
      .in(jsonSettingsIn)
      .out(jsonSettingsOut)

  // Endpoints for Lists
  val userListsEndpoint: PublicEndpoint[(String, String, String), Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's lists endpoint")
      .description("This endpoint returns all the lists for a user")
      .get
      .in(pathUsername)
      .in("lists")
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, MediaContentList.Id), Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's specific list endpoint")
      .description("This endpoint returns a specific list for a user by the ID of the list")
      .get
      .in(pathUsername)
      .in("lists")
      .in(pathListId)
      .out(jsonListOfElementListOut)

  


  // Endpoints para el aspecto social de los User
  val userFollowerList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's followers endpoint")
      .description("This endpoint returns the followers of the user")
      .get
      .in(pathUsername)
      .in("followers")
      .out(jsonUserListOut)

  val userFollowingList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's people following endpoint")
      .description("This endpoint returns the people followed by the user")
      .get
      .in(pathUsername)
      .in("following")
      .out(jsonUserListOut)

  val userChatList: PublicEndpoint[String, Unit, List[Chat], Any] =
    userBaseEndpoint
      .name("User's chats endpoint")
      .description("This endpoint returns the chats of the user")
      .get
      .in(pathUsername)
      .in("chats")
      .out(jsonChatListOut)

  val userChat: PublicEndpoint[String, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's specific chat endpoint")
      .description("This endpoint returns a specific chat of the user by the ID of the chat")
      .get
      .in(pathUsername)
      .in("chat")
      .out(jsonChatOut)

  val userChatWithOtherUser: PublicEndpoint[String, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's chat with other user endpoint")
      .description("This endpoint returns a the chat between the user and another specific user")
      .get
      .in(pathUsername)
      .in("chat")
      .out(jsonChatOut)
}
