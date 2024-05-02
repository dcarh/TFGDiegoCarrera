package modelClasses.social

import modelClasses.media.MediaMainContent
import modelClasses.user.User

case class Like(
               id       : Like.Id,
               elementId: Either[MediaMainContent.Id, UserGeneratedContent.Id],
               userId   : User.Id
               )
object Like {
   type Id = Long
}