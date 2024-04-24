package modelClasses

import sttp.tapir.generic.auto._

case class Like(
                 id: Like.Id,
                 elementId: Either[MediaContent.Id, UserContent.Id],
                 userId: User.Id
               )
object Like {
   type Id = Long
}