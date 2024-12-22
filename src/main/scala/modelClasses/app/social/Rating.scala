package modelClasses.app.social

import modelClasses.ids.Media.{MovieId, TVShowId, SeasonNumber, EpisodeNumber, VideogameId, BookId}
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId

case class Rating(
                 id          : RatingId,
                 userId      : UserId,
                 mediaRatedId: MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId,
                 rating      : Int
                 )

// object Rating {
//   type Id = Long
// }
// TODO: ¿Realmente hace falta esta clase cuando ya tenemos al atributo "rating" en Entry?