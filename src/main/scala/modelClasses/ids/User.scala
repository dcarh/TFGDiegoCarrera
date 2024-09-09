package modelClasses.ids

import io.circe.generic.auto.*

object User {

  case class UserId(value: Long)

}
