package server.routes

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes

import server.routes.chatting.ChatsRoutes.chatsRoutes
import server.routes.chatting.MessagesRoutes.messagesRoutes
import server.routes.social.EntriesRoutes.entriesRoutes
import server.routes.social.LikesRoutes.likesRoutes
import server.routes.social.MediaListsRoutes.mediaListsRoutes
import server.routes.social.RatingsRoutes.ratingsRoutes
import server.routes.social.RepliesRoutes.repliesRoutes
import server.routes.social.ReviewsRoutes.reviewsRoutes
import server.routes.user.chatting.UserChatsRoutes.userChatsRoutes
import server.routes.user.media.UserCompletedMediaRoutes.userCompletedMediaRoutes
import server.routes.user.media.UserDroppedMediaRoutes.userDroppedMediaRoutes
import server.routes.user.media.UserFavouritesRoutes.userFavouritesRoutes
import server.routes.user.media.UserInProgressMediaRoutes.userInProgressMediaRoutes
import server.routes.user.media.UserOnHoldMediaRoutes.userOnHoldMediaRoutes
import server.routes.user.media.UserPendingMediaRoutes.userPendingMediaRoutes
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
    chatsRoutes <+>
      messagesRoutes <+>
      entriesRoutes <+>
      likesRoutes <+>
      mediaListsRoutes <+>
      ratingsRoutes <+>
      repliesRoutes <+>
      reviewsRoutes <+>
      userChatsRoutes <+>
      userCompletedMediaRoutes <+>
      userDroppedMediaRoutes <+>
      userFavouritesRoutes <+>
      userInProgressMediaRoutes <+>
      userOnHoldMediaRoutes <+>
      userPendingMediaRoutes <+>
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
      searchRoutes
}
