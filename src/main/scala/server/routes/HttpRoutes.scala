package server.routes

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes

import server.routes.chatting.ChattingRoutes.chattingRoutes
import server.routes.social.EntriesRoutes.entriesRoutes
import server.routes.social.LikesRoutes.likesRoutes
import server.routes.social.MediaListsRoutes.mediaListsRoutes
import server.routes.social.RatingsRoutes.ratingsRoutes
import server.routes.social.RepliesRoutes.repliesRoutes
import server.routes.social.ReviewsRoutes.reviewsRoutes
import server.routes.user.media.UserMediaRoutes.userMediaRoutes
import server.routes.user.media.UserFavouritesRoutes.userFavouritesRoutes
import server.routes.user.network.UserNetworkRoutes.userNetworkRoutes
import server.routes.user.social.UserEntriesRoutes.userEntriesRoutes
import server.routes.user.social.UserLikesRoutes.userLikesRoutes
import server.routes.user.social.UserListsRoutes.userMediaListsRoutes
import server.routes.user.social.UserRatingsRoutes.userRatingsRoutes
import server.routes.user.social.UserRepliesRoutes.userRepliesRoutes
import server.routes.user.social.UserReviewsRoutes.userReviewsRoutes
import server.routes.user.UserProfileRoutes.userProfileRoutes
import server.routes.user.UserRoutes.userRoutes
import server.routes.user.UserSettingsRoutes.userSettingsRoutes
import server.routes.search.SearchRoutes.searchRoutes

object HttpRoutes {

  val httpRoutes: HttpRoutes[IO] =
    chattingRoutes <+>
      entriesRoutes <+>
      likesRoutes <+>
      mediaListsRoutes <+>
      ratingsRoutes <+>
      repliesRoutes <+>
      reviewsRoutes <+>
      userFavouritesRoutes <+>
      userNetworkRoutes <+>
      userEntriesRoutes <+>
      userLikesRoutes <+>
      userMediaListsRoutes <+>
      userRatingsRoutes <+>
      userRepliesRoutes <+>
      userReviewsRoutes <+>
      userRoutes <+>
      userProfileRoutes <+>
      userSettingsRoutes <+>
      searchRoutes <+>
      userMediaRoutes
}
