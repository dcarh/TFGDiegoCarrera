package modelClasses.app.social

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

case class Like(
               id       : Like.Id,
               userId   : User.Id,
               elementId: Movie.Id | TVShow.Id | Videogame.Id | Book.Id | MediaContentList.Id | Review.Id | Reply.Id
               )
object Like {
   type Id = Long
}