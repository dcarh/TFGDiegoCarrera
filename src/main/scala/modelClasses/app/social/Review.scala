package modelClasses.app.social

import upickle.default.*

import io.circe.generic.auto.*

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Review(
                   id            : ReviewId,
                   user          : UserId,
                   objectReviewed: MovieId | TVShowId | VideogameId | BookId,
                   review        : String,
                   likes         : List[LikeId],
                   replies       : List[ReplyId],
                   visibility    : Visibility
                 ) derives ReadWriter

// object Review {
//   type Id = Long
// }
