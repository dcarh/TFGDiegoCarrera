package modelClasses.app.social

import io.circe.generic.auto.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class Review(
                   id            : ReviewId,
                   user          : UserId,
                   objectReviewed: MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId, // Todo: Meter aquí también temporadaras y episodios (para que Entry y Rating concuerden)
                   // TODO: No tiene sentido que Entry tenga temporadas y episodios, y Review, que es subconjunto de ella, no
                   review        : String,
                   likes         : List[LikeId],
                   allowReplies  : Boolean,
                   replies       : List[ReplyId],
                   spoilers      : Boolean
                 )

// object Review {
//   type Id = Long
// }
