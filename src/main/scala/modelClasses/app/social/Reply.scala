package modelClasses.app.social

import upickle.default.*
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Reply(
                  id           : ReplyId,
                  user         : UserId,
                  // objectReplied: Either[MediaContentList.Id, UserGeneratedContent.Id],
                  objectReplied: MovieId | TVShowId | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId,    
                  //TODO: ¿¿¿¿ Movie, TVShow, Videogame, Book ????
                  likes        : List[LikeId],
                  replies      : List[ReplyId],
                  visibility   : Visibility
                ) derives ReadWriter

// object Reply {
//   type Id = Long
// }
