package modelClasses.app.social

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

case class Reply(
                  id           : Reply.Id,
                  user         : User.Id,
                  // objectReplied: Either[MediaContentList.Id, UserGeneratedContent.Id],
                  objectReplied: Movie.Id | TVShow.Id | Videogame.Id | Book.Id | MediaContentList.Id | Review.Id | Reply.Id,    
                  //TODO: ¿¿¿¿ Movie, TVShow, Videogame, Book ????
                  likes        : List[Like.Id],
                  replies      : List[Reply.Id],
                  visibility   : Visibility
                )

object Reply {
  type Id = Long
}
