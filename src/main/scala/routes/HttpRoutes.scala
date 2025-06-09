package routes

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes

import routes.chatting.ChattingRoutes.chattingRoutes
import routes.media.MovieRoutes.movieRoutes
import routes.media.TvShowRoutes.tvShowRoutes
import routes.media.TvSeasonRoutes.tvSeasonRoutes
import routes.media.TvEpisodeRoutes.tvEpisodeRoutes
import routes.media.VideogameRoutes.videogameRoutes
import routes.media.BookRoutes.bookRoutes
import routes.media.MovieRoutes.movieRoutes
import routes.social.EntriesRoutes.entriesRoutes
import routes.social.LikesRoutes.likesRoutes
import routes.social.MediaListsRoutes.mediaListsRoutes
import routes.social.RatingsRoutes.ratingsRoutes
import routes.social.RepliesRoutes.repliesRoutes
import routes.social.ReviewsRoutes.reviewsRoutes
import routes.search.SearchRoutes.searchRoutes
import routes.user.media.UserMediaRoutes.userMediaRoutes
import routes.user.media.UserFavouritesRoutes.userFavouritesRoutes
import routes.user.network.UserNetworkRoutes.userNetworkRoutes
import routes.user.social.UserEntriesRoutes.userEntriesRoutes
import routes.user.social.UserLikesRoutes.userLikesRoutes
import routes.user.social.UserMediaListsRoutes.userMediaListsRoutes
import routes.user.social.UserRatingsRoutes.userRatingsRoutes
import routes.user.social.UserRepliesRoutes.userRepliesRoutes
import routes.user.social.UserReviewsRoutes.userReviewsRoutes
import routes.user.UserRoutes.userRoutes

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
