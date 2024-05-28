package modelClasses.social

import io.circe.generic.auto._

import modelClasses.media.{Book, Movie, TVShow, Videogame}
import modelClasses.user.User

case class MediaContentList(
                             id           : MediaContentList.Id,
                             user         : User.Id,
                             //mediaContents: List[MediaMainContent.Id],
                             mediaContents: List[Movie.Id | TVShow.Id | Videogame.Id | Book.Id],
                             likes        : List[Like.Id],
                             replies      : List[Reply.Id],
                             visibility   : Visibility
                           )

object MediaContentList {
  type Id = Long
}
