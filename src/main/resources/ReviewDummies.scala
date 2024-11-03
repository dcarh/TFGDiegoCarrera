import modelClasses.app.social.Review

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

object ReviewDummies {
  val review1: Review = new Review()
  val review2: Review = new Review()
  val review3: Review = new Review()
  val review4: Review = new Review()
  val review5: Review = new Review()
}