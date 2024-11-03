package modelClasses.app.social

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Like(
               id       : LikeId,
               userId   : UserId,
               elementId: MovieId | TVShowId | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId
               )

// object Like {
//    type Id = Long
// }