package modelClasses.app.social

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

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