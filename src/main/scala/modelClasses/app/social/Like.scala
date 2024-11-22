package modelClasses.app.social

import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class Like(
               id       : LikeId,
               userId   : UserId,
               elementId: MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId
               )

// object Like {
//    type Id = Long
// }