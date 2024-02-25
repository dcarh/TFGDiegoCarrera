package api

import io.circe.generic.auto._
import modelClasses.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Review, Comment, Article, 
  UserSettings, ErrorInfo, Chat}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.model.StatusCode

class UserEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathUserId: EndpointInput[Int] =
    path[Int]("user_id")

  private val pathUsername: EndpointInput[String] =
    path[String]("username")

  private val pathListId: EndpointInput[Int] =
    path[Int]("list_id")

  private val pathArticleId: EndpointInput[Int] =
    path[Int]("article_id")

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

  private val jsonElementListOut: EndpointOutput[Seq[Element]] =
    jsonBody[Seq[Element]]

  private val jsonMovieListOut: EndpointOutput[Seq[Movie]] =
    jsonBody[Seq[Movie]]

  private val jsonTVShowListOut: EndpointOutput[Seq[TVShow]] =
    jsonBody[Seq[TVShow]]

  private val jsonSeasonListOut: EndpointOutput[Seq[Season]] =
    jsonBody[Seq[Season]]

  private val jsonEpisodeListOut: EndpointOutput[Seq[Episode]] =
    jsonBody[Seq[Episode]]

  private val jsonVideogameListOut: EndpointOutput[Seq[Videogame]] =
    jsonBody[Seq[Videogame]]

  private val jsonBookListOut: EndpointOutput[Seq[Book]] =
    jsonBody[Seq[Book]]

  private val jsonReviewListOut: EndpointOutput[Seq[Review]] =
    jsonBody[Seq[Review]]

  private val jsonCommentListOut: EndpointOutput[Seq[Comment]] =
    jsonBody[Seq[Comment]]

  private val jsonArticleListOut: EndpointOutput[Seq[Article]] =
    jsonBody[Seq[Article]]

  private val jsonUserListOut: EndpointOutput[Seq[User]] =
    jsonBody[Seq[User]]

  private val jsonChatListOut: EndpointOutput[Seq[Chat]] =
    jsonBody[Seq[Chat]]
  
  private val jsonListOfElementListOut: EndpointOutput[Seq[ElementList]] =
    jsonBody[Seq[ElementList]]

  private val jsonSettingsOut: EndpointOutput[UserSettings] =
    jsonBody[UserSettings]

  private val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
    jsonBody[ErrorInfo]

  private val jsonSettingsIn: EndpointInput[UserSettings] =
    jsonBody[UserSettings]

  private val jsonArticleIn: EndpointInput[Article] =
    jsonBody[Article]

  private val jsonElementListIn: EndpointInput[ElementList] =
    jsonBody[ElementList]


  // Endpoint that returns a list with all the users in the app
  val usersEndpoint: PublicEndpoint[String, Unit, Seq[User], Any] =
    usersBaseEndpoint
      .in(queryOrderBy)
      .out(jsonUserListOut)
  

  // Endpoints for elements of "Completed" category
  val userCompletedListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed")
      .out(jsonElementListOut)

  val userCompletedMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "movies")
      .out(jsonMovieListOut)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "tv_shows")
      .out(jsonTVShowListOut)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "seasons")
      .out(jsonSeasonListOut)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "episodes")
      .out(jsonEpisodeListOut)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "videogames")
      .out(jsonVideogameListOut)

  val userCompletedBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "In Progress" category
  val userInProgressListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress")
      .out(jsonElementListOut)

  val userInProgressMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "movies")
      .out(jsonMovieListOut)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "tv_shows")
      .out(jsonTVShowListOut)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "seasons")
      .out(jsonSeasonListOut)

  val userInProgressEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("in_progress" / "episodes")
    .out(jsonEpisodeListOut)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "videogames")
      .out(jsonVideogameListOut)

  val userInProgressBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Pending" category
  val userPendingListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending")
      .out(jsonElementListOut)

  val userPendingMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "movies")
      .out(jsonMovieListOut)

  val userPendingTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "tv_shows")
      .out(jsonTVShowListOut)

  val userPendingSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "seasons")
      .out(jsonSeasonListOut)

  val userPendingEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("pending" / "episodes")
    .out(jsonEpisodeListOut)

  val userPendingVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "videogames")
      .out(jsonVideogameListOut)

  val userPendingBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "On Hold" category
  val userOnHoldListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold")
      .out(jsonElementListOut)

  val userOnHoldMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "movies")
      .out(jsonMovieListOut)

  val userOnHoldTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "tv_shows")
      .out(jsonTVShowListOut)

  val userOnHoldSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "seasons")
      .out(jsonSeasonListOut)

  val userOnHoldEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("on_hold" / "episodes")
    .out(jsonEpisodeListOut)

  val userOnHoldVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "videogames")
      .out(jsonVideogameListOut)

  val userOnHoldBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Abandoned" category
  val userAbandonedListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned")
      .out(jsonElementListOut)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "movies")
      .out(jsonMovieListOut)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "tv_shows")
      .out(jsonTVShowListOut)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "seasons")
      .out(jsonSeasonListOut)

  val userAbandonedEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("abandoned" / "episodes")
    .out(jsonEpisodeListOut)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "videogames")
      .out(jsonVideogameListOut)

  val userAbandonedBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Wishlist" category
  val userWishlistListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist")
      .out(jsonElementListOut)

  val userWishlistMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "movies")
      .out(jsonMovieListOut)

  val userWishlistTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "tv_shows")
      .out(jsonTVShowListOut)

  val userWishlistSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "seasons")
      .out(jsonSeasonListOut)

  val userWishlistEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("wishlist" / "episodes")
    .out(jsonEpisodeListOut)

  val userWishlistVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "videogames")
      .out(jsonVideogameListOut)

  val userWishlistBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Owned" category
  val userOwnedListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned")
      .out(jsonElementListOut)

  val userOwnedMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "movies")
      .out(jsonMovieListOut)

  val userOwnedTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "tv_shows")
      .out(jsonTVShowListOut)

  val userOwnedSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "seasons")
      .out(jsonSeasonListOut)

  val userOwnedEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("owned" / "episodes")
    .out(jsonEpisodeListOut)

  val userOwnedVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "videogames")
      .out(jsonVideogameListOut)

  val userOwnedBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Liked" category
  val userLikesListEndpoint: PublicEndpoint[String, Unit, Seq[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes")
      .out(jsonElementListOut)

  val userLikedMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "movies")
      .out(jsonMovieListOut)

  val userLikedTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "tv_shows")
      .out(jsonTVShowListOut)

  val userLikedSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "seasons")
      .out(jsonSeasonListOut)

  val userLikedEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("likes" / "episodes")
    .out(jsonEpisodeListOut)

  val userLikedVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "videogames")
      .out(jsonVideogameListOut)

  val userLikedBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "books")
      .out(jsonBookListOut)

  val userLikedReviewsListEndpoint: PublicEndpoint[String, Unit, Seq[Review], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "reviews")
      .out(jsonReviewListOut)

  val userLikedCommentsListEndpoint: PublicEndpoint[String, Unit, Seq[Comment], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "comments")
      .out(jsonCommentListOut)

  val userLikedListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists")
      .out(jsonListOfElementListOut)

  val userLikedMovieListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "movies")
      .out(jsonListOfElementListOut)

  val userLikedSeasonsListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "seasons")
      .out(jsonListOfElementListOut)

  val userLikedTVShowsListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "tv_shows")
      .out(jsonListOfElementListOut)

  val userLikedEpisodesListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "episodes")
      .out(jsonListOfElementListOut)

  val userLikedVideogamesListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "videogames")
      .out(jsonListOfElementListOut)

  val userLikedBooksListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "books")
      .out(jsonListOfElementListOut)



  // Endpoints for Reviews
  val userReviewsListEndpoint: PublicEndpoint[String, Unit, Seq[Review], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews")
      .out(jsonReviewListOut)

  val userReviewsMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews" / "movies")
      .out(jsonMovieListOut)

  val userReviewsTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews" / "tv_shows")
      .out(jsonTVShowListOut)

  val userReviewsSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews" / "seasons")
      .out(jsonSeasonListOut)

  val userReviewsEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("reviews" / "episodes")
    .out(jsonEpisodeListOut)

  val userReviewsVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews" / "videogames")
      .out(jsonVideogameListOut)

  val userReviewsBooksListEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews" / "books")
      .out(jsonBookListOut)

  val userReviewsArticlesListEndpoint: PublicEndpoint[String, Unit, Seq[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("reviews" / "articles")
      .out(jsonArticleListOut)



  // Endpoints for favourite Movies, TV Shows, Seasons, Episodes, Videogames and Books
  val userFavouriteMoviesListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "movies")
      .out(jsonListOfElementListOut)

  val userFavouriteTVShowsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "tv_shows")
      .out(jsonListOfElementListOut)

  val userFavouriteSeasonsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "seasons")
      .out(jsonListOfElementListOut)

  val userFavouriteEpisodesListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "episodes")
      .out(jsonListOfElementListOut)

  val userFavouriteVideogamesListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "videogames")
      .out(jsonListOfElementListOut)

  val userFavouriteBooksListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "books")
      .out(jsonListOfElementListOut)



  // Endpoints for Comments
  val userCommentsListEndpoint: PublicEndpoint[String, Unit, Seq[Comment], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("comments")
    .out(jsonCommentListOut)

  val userCommentsListsListEndpoint: PublicEndpoint[String, Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("comments" / "lists")
      .out(jsonListOfElementListOut)

  val userCommentsReviewsListEndpoint: PublicEndpoint[String, Unit, Seq[Review], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("comments" / "reviews")
      .out(jsonReviewListOut)

  val userCommentsCommentsListEndpoint: PublicEndpoint[String, Unit, Seq[Comment], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("comments" / "comments")
      .out(jsonCommentListOut)



  // Endpoints for Settings
  val userSettingsEndpoint: PublicEndpoint[String, Unit, UserSettings, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("settings")
      .out(jsonSettingsOut)

  val userEditSettingsEndpoint: PublicEndpoint[(String, UserSettings), Unit, UserSettings, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("settings" / "edit")
      .in(jsonSettingsIn)
      .out(jsonSettingsOut)

  
  // Endpoints for Articles
  val userOwnArticlesListEndpoint: PublicEndpoint[String, Unit, Seq[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "own")
      .out(jsonArticleListOut)

  val userSavedArticlesListEndpoint: PublicEndpoint[String, Unit, Seq[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "saved")
      .out(jsonArticleListOut)

  val userSoldArticlesListEndpoint: PublicEndpoint[String, Unit, Seq[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "sold")
      .out(jsonArticleListOut)

  val userBoughtArticlesListEndpoint: PublicEndpoint[String, Unit, Seq[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "bought")
      .out(jsonArticleListOut)

  val userCreateArticleEndpoint: PublicEndpoint[(String, Article), Unit, Seq[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "create")
      .in(jsonArticleIn)
      .out(jsonArticleListOut)

  val userDeleteArticleEndpoint: PublicEndpoint[(String, Int), ErrorInfo, Unit, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles")
      .in(pathArticleId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(jsonErrorInfoOut)



  // Endpoints for Lists
  val userListsEndpoint: PublicEndpoint[(String, String, String), Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("lists")
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, Int), Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("lists")
      .in(pathListId)
      .out(jsonListOfElementListOut)

  val userCreateListEndpoint: PublicEndpoint[(String, ElementList), Unit, Seq[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("lists" / "create")
      .in(jsonElementListIn)
      .out(jsonListOfElementListOut)

  val userDeleteListEndpoint: PublicEndpoint[(String, Int), ErrorInfo, Unit, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("lists")
      .in(pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(jsonErrorInfoOut)


  // Endpoints para el aspecto social de los User
  val userFollowerList: PublicEndpoint[String, Unit, Seq[User], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("followers")
      .out(jsonUserListOut)

  val userFollowingList: PublicEndpoint[String, Unit, Seq[User], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("following")
      .out(jsonUserListOut)

  val userChatList: PublicEndpoint[String, Unit, Seq[Chat], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("chats")
      .out(jsonChatListOut)
}
