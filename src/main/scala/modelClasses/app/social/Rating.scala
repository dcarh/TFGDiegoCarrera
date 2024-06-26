package modelClasses.app.social

import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.user.User

case class Rating(
                 id: Rating.Id,
                 userId: User.Id,
                 mediaRated: Movie.Id | TVShow.Id | (TVShow.Id, Season.Number) | (TVShow.Id, Season.Number, Episode.Number) | Videogame.Id | Book.Id,
                 rating: Int
                 )

object Rating {
  type Id = Long
}
// TODO: ¿Realmente hace falta esta clase cuando ya tenemos al atributo "rating" en Entry?