package modelClasses.app.social

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

case class Review(
                   id            : Review.Id,
                   user          : User.Id,
                   objectReviewed: Movie.Id | TVShow.Id | Videogame.Id | Book.Id,
                   review        : String,
                   likes         : List[Like.Id],
                   replies       : List[Reply.Id],
                   visibility    : Visibility
                 )

object Review {
  type Id = Long
}
