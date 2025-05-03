package server.routes

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes

import server.routes.chatting.ChattingRoutes.chattingRoutes
import server.routes.media.MovieRoutes.movieRoutes
import server.routes.media.TvShowRoutes.tvShowRoutes
import server.routes.media.TvSeasonRoutes.tvSeasonRoutes
import server.routes.media.TvEpisodeRoutes.tvEpisodeRoutes
import server.routes.media.VideogameRoutes.videogameRoutes
import server.routes.media.BookRoutes.bookRoutes
import server.routes.media.MovieRoutes.movieRoutes
import server.routes.social.EntriesRoutes.entriesRoutes
import server.routes.social.LikesRoutes.likesRoutes
import server.routes.social.MediaListsRoutes.mediaListsRoutes
import server.routes.social.RatingsRoutes.ratingsRoutes
import server.routes.social.RepliesRoutes.repliesRoutes
import server.routes.social.ReviewsRoutes.reviewsRoutes
import server.routes.search.SearchRoutes.searchRoutes
import server.routes.user.media.UserMediaRoutes.userMediaRoutes
import server.routes.user.media.UserFavouritesRoutes.userFavouritesRoutes
import server.routes.user.network.UserNetworkRoutes.userNetworkRoutes
import server.routes.user.social.UserEntriesRoutes.userEntriesRoutes
import server.routes.user.social.UserLikesRoutes.userLikesRoutes
import server.routes.user.social.UserMediaListsRoutes.userMediaListsRoutes
import server.routes.user.social.UserRatingsRoutes.userRatingsRoutes
import server.routes.user.social.UserRepliesRoutes.userRepliesRoutes
import server.routes.user.social.UserReviewsRoutes.userReviewsRoutes
import server.routes.user.UserRoutes.userRoutes

object HttpRoutes {

  val httpRoutes: HttpRoutes[IO] =
    chattingRoutes         <+>
      entriesRoutes        <+>
      likesRoutes          <+>
      movieRoutes          <+>
      tvShowRoutes         <+>
      tvSeasonRoutes       <+>
      tvEpisodeRoutes      <+>
      videogameRoutes      <+>
      bookRoutes           <+>
      mediaListsRoutes     <+>
      ratingsRoutes        <+>
      repliesRoutes        <+>
      reviewsRoutes        <+>
      searchRoutes         <+>
      userRoutes           <+>
      userFavouritesRoutes <+>
      userNetworkRoutes    <+>
      userEntriesRoutes    <+>
      userLikesRoutes      <+>
      userMediaListsRoutes <+>
      userRatingsRoutes    <+>
      userRepliesRoutes    <+>
      userReviewsRoutes    <+>
      userMediaRoutes      
}
