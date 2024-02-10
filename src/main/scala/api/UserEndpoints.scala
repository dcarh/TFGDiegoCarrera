package api

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import model.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Log, Comment, Article, 
  Settings, Review, ErrorInfo, Chat}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword
import sttp.model.StatusCode

import java.util.UUID

class UserEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  val user1: User = User(727891, "d.carrerah.2019@alumnos.urjc.es", UsernamePassword("dcarrerah",
    Some("6756897845563")), null)

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

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // val userBaseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
  //  endpoint.in("api" / "v1.0").errorOut(jsonBody[ErrorInfo])

  private val jsonElementListOut: EndpointOutput[List[Element]] =
    jsonBody[List[Element]]

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

  private val jsonLogListOut: EndpointOutput[List[Log]] =
    jsonBody[List[Log]]

  private val jsonArticleListOut: EndpointOutput[List[Article]] =
    jsonBody[List[Article]]

  private val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  private val jsonChatListOut: EndpointOutput[List[Chat]] =
    jsonBody[List[Chat]]
  
  private val jsonListOfElementListOut: EndpointOutput[List[ElementList]] =
    jsonBody[List[ElementList]]

  private val jsonSettingsOut: EndpointOutput[Settings] =
    jsonBody[Settings]

  private val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
    jsonBody[ErrorInfo]

  private val jsonSettingsIn: EndpointInput[Settings] =
    jsonBody[Settings]

  private val jsonArticleIn: EndpointInput[Article] =
    jsonBody[Article]

  private val jsonElementListIn: EndpointInput[ElementList] =
    jsonBody[ElementList]



  // Endpoints for elements of "Completed" category
  val userCompletedListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed")
      .out(jsonElementListOut)

  val userCompletedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "movies")
      .out(jsonMovieListOut)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "tv_shows")
      .out(jsonTVShowListOut)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "seasons")
      .out(jsonSeasonListOut)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "episodes")
      .out(jsonEpisodeListOut)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "videogames")
      .out(jsonVideogameListOut)

  val userCompletedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("completed" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "In Progress" category
  val userInProgressListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress")
      .out(jsonElementListOut)

  val userInProgressMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "movies")
      .out(jsonMovieListOut)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "tv_shows")
      .out(jsonTVShowListOut)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "seasons")
      .out(jsonSeasonListOut)

  val userInProgressEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("in_progress" / "episodes")
    .out(jsonEpisodeListOut)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "videogames")
      .out(jsonVideogameListOut)

  val userInProgressBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("in_progress" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Pending" category
  val userPendingListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending")
      .out(jsonElementListOut)

  val userPendingMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "movies")
      .out(jsonMovieListOut)

  val userPendingTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "tv_shows")
      .out(jsonTVShowListOut)

  val userPendingSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "seasons")
      .out(jsonSeasonListOut)

  val userPendingEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("pending" / "episodes")
    .out(jsonEpisodeListOut)

  val userPendingVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "videogames")
      .out(jsonVideogameListOut)

  val userPendingBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("pending" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "On Hold" category
  val userOnHoldListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold")
      .out(jsonElementListOut)

  val userOnHoldMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "movies")
      .out(jsonMovieListOut)

  val userOnHoldTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "tv_shows")
      .out(jsonTVShowListOut)

  val userOnHoldSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "seasons")
      .out(jsonSeasonListOut)

  val userOnHoldEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("on_hold" / "episodes")
    .out(jsonEpisodeListOut)

  val userOnHoldVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "videogames")
      .out(jsonVideogameListOut)

  val userOnHoldBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("on_hold" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Abandoned" category
  val userAbandonedListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned")
      .out(jsonElementListOut)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "movies")
      .out(jsonMovieListOut)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "tv_shows")
      .out(jsonTVShowListOut)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "seasons")
      .out(jsonSeasonListOut)

  val userAbandonedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("abandoned" / "episodes")
    .out(jsonEpisodeListOut)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "videogames")
      .out(jsonVideogameListOut)

  val userAbandonedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("abandoned" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Wishlist" category
  val userWishlistListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist")
      .out(jsonElementListOut)

  val userWishlistMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "movies")
      .out(jsonMovieListOut)

  val userWishlistTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "tv_shows")
      .out(jsonTVShowListOut)

  val userWishlistSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "seasons")
      .out(jsonSeasonListOut)

  val userWishlistEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("wishlist" / "episodes")
    .out(jsonEpisodeListOut)

  val userWishlistVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "videogames")
      .out(jsonVideogameListOut)

  val userWishlistBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("wishlist" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Owned" category
  val userOwnedListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned")
      .out(jsonElementListOut)

  val userOwnedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "movies")
      .out(jsonMovieListOut)

  val userOwnedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "tv_shows")
      .out(jsonTVShowListOut)

  val userOwnedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "seasons")
      .out(jsonSeasonListOut)

  val userOwnedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("owned" / "episodes")
    .out(jsonEpisodeListOut)

  val userOwnedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "videogames")
      .out(jsonVideogameListOut)

  val userOwnedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("owned" / "books")
      .out(jsonBookListOut)



  // Endpoints for elements of "Liked" category
  val userLikesListEndpoint: PublicEndpoint[String, Unit, List[Element], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes")
      .out(jsonElementListOut)

  val userLikedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "movies")
      .out(jsonMovieListOut)

  val userLikedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "tv_shows")
      .out(jsonTVShowListOut)

  val userLikedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "seasons")
      .out(jsonSeasonListOut)

  val userLikedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("likes" / "episodes")
    .out(jsonEpisodeListOut)

  val userLikedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "videogames")
      .out(jsonVideogameListOut)

  val userLikedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "books")
      .out(jsonBookListOut)

  val userLikedReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "reviews")
      .out(jsonReviewListOut)

  val userLikedCommentsListEndpoint: PublicEndpoint[String, Unit, List[Comment], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "comments")
      .out(jsonCommentListOut)

  val userLikedListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists")
      .out(jsonListOfElementListOut)

  val userLikedMovieListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "movies")
      .out(jsonListOfElementListOut)

  val userLikedSeasonsListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "seasons")
      .out(jsonListOfElementListOut)

  val userLikedTVShowsListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "tv_shows")
      .out(jsonListOfElementListOut)

  val userLikedEpisodesListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "episodes")
      .out(jsonListOfElementListOut)

  val userLikedVideogamesListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "videogames")
      .out(jsonListOfElementListOut)

  val userLikedBooksListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("likes" / "lists" / "books")
      .out(jsonListOfElementListOut)



  // Endpoints for Logs
  val userLogsListEndpoint: PublicEndpoint[String, Unit, List[Log], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs")
      .out(jsonLogListOut)

  val userLogsMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs" / "movies")
      .out(jsonMovieListOut)

  val userLogsTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs" / "tv_shows")
      .out(jsonTVShowListOut)

  val userLogsSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs" / "seasons")
      .out(jsonSeasonListOut)

  val userLogsEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("logs" / "episodes")
    .out(jsonEpisodeListOut)

  val userLogsVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs" / "videogames")
      .out(jsonVideogameListOut)

  val userLogsBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs" / "books")
      .out(jsonBookListOut)

  val userLogsArticlesListEndpoint: PublicEndpoint[String, Unit, List[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("logs" / "articles")
      .out(jsonArticleListOut)



  // Endpoints for favourite Movies, TV Shows, Seasons, Episodes, Videogames and Books
  val userFavouriteMoviesListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "movies")
      .out(jsonListOfElementListOut)

  val userFavouriteTVShowsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "tv_shows")
      .out(jsonListOfElementListOut)

  val userFavouriteSeasonsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "seasons")
      .out(jsonListOfElementListOut)

  val userFavouriteEpisodesListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "episodes")
      .out(jsonListOfElementListOut)

  val userFavouriteVideogamesListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "videogames")
      .out(jsonListOfElementListOut)

  val userFavouriteBooksListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("favourites" / "books")
      .out(jsonListOfElementListOut)



  // Endpoints for Comments
  val userCommentsListEndpoint: PublicEndpoint[String, Unit, List[Comment], Any] =
  userBaseEndpoint
    .in(pathUsername)
    .in("comments")
    .out(jsonCommentListOut)

  val userCommentsListsListEndpoint: PublicEndpoint[String, Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("comments" / "lists")
      .out(jsonListOfElementListOut)

  val userCommentsReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("comments" / "reviews")
      .out(jsonReviewListOut)

  val userCommentsCommentsListEndpoint: PublicEndpoint[String, Unit, List[Comment], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("comments" / "comments")
      .out(jsonCommentListOut)



  // Endpoints for Settings
  val userSettingsEndpoint: PublicEndpoint[String, Unit, Settings, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("settings")
      .out(jsonSettingsOut)

  val userEditSettingsEndpoint: PublicEndpoint[(String, Settings), Unit, Settings, Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("settings" / "edit")
      .in(jsonSettingsIn)
      .out(jsonSettingsOut)

  
  // Endpoints for Articles
  val userOwnArticlesListEndpoint: PublicEndpoint[String, Unit, List[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "own")
      .out(jsonArticleListOut)

  val userSavedArticlesListEndpoint: PublicEndpoint[String, Unit, List[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "saved")
      .out(jsonArticleListOut)

  val userSoldArticlesListEndpoint: PublicEndpoint[String, Unit, List[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "sold")
      .out(jsonArticleListOut)

  val userBoughtArticlesListEndpoint: PublicEndpoint[String, Unit, List[Article], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("articles" / "bought")
      .out(jsonArticleListOut)

  val userCreateArticleEndpoint: PublicEndpoint[(String, Article), Unit, List[Article], Any] =
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
  val userListsEndpoint: PublicEndpoint[(String, String, String), Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("lists")
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, Int), Unit, List[ElementList], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("lists")
      .in(pathListId)
      .out(jsonListOfElementListOut)

  val userCreateListEndpoint: PublicEndpoint[(String, ElementList), Unit, List[ElementList], Any] =
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
  val userFollowerList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("followers")
      .out(jsonUserListOut)

  val userFollowingList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("following")
      .out(jsonUserListOut)

  val userChatList: PublicEndpoint[String, Unit, List[Chat], Any] =
    userBaseEndpoint
      .in(pathUsername)
      .in("chats")
      .out(jsonChatListOut)
}
