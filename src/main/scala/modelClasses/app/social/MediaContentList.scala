package modelClasses.app.social

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

import io.circe.generic.auto.*

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReplyId}
import modelClasses.ids.User.UserId

case class MediaContentList(
                             id           : MediaContentListId,
                             user         : UserId,
                             mediaContents: List[MovieId | TVShowId | VideogameId | BookId],
                             likes        : List[LikeId],
                             replies      : List[ReplyId],
                             visibility   : Visibility
                           )

// object MediaContentList {
//   type Id = Long
// }
