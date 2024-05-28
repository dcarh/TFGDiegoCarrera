package modelClasses.social

import modelClasses.media.{Book, Movie, TVShow, Videogame}
import modelClasses.user.User

case class Reply(
                  id           : Reply.Id,
                  user         : User.Id,
                  // objectReplied: Either[MediaContentList.Id, UserGeneratedContent.Id],
                  objectReplied: Movie.Id | TVShow.Id | Videogame.Id | Book.Id | MediaContentList.Id | Review.Id | Reply.Id,
                  likes        : List[Like.Id],
                  replies      : List[Reply.Id],
                  visibility   : Visibility
                )

object Reply {
  type Id = Long
}
