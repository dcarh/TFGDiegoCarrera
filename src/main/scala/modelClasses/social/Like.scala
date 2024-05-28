package modelClasses.social

import modelClasses.media.{Book, Movie, TVShow, Videogame}
import modelClasses.user.User

case class Like(
               id       : Like.Id,
               // elementId: Either[MediaMainContent.Id, UserGeneratedContent.Id],
               elementId: Movie.Id | TVShow.Id | Videogame.Id | Book.Id | MediaContentList.Id | Review.Id | Reply.Id,
               userId   : User.Id
               )
object Like {
   type Id = Long
}