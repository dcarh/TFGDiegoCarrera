package modelClasses.app.social

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
                )

// object Reply {
//   type Id = Long
// }
