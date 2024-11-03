import modelClasses.app.user.User

import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaContentListId, RatingId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

object UserDummies {
  val user1: User = new User()
  val user2: User = new User()
  val user3: User = new User()
  val user4: User = new User()
  val user5: User = new User()
}