package modelClasses.social

import modelClasses.media._
import modelClasses.user.User

case class Rating(
                 id: Rating.Id,
                 userId: User.Id,
                 mediaRated: Movie.Id | TVShow.Id | Season.Id | Episode.Id | Videogame.Id | Book.Id,
                 rating: Int
                 )

object Rating {
  type Id = Long
}
// TODO: ¿Realmente hace falta esta clase cuando ya tenemos al atributo "rating" en Entry?