package modelClasses

import sttp.tapir.generic.auto._

case class UserSettings(
                       user    : User.Id,
                       isPublic: Boolean
                       )

