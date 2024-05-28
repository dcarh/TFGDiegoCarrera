package modelClasses.social

import io.circe.generic.auto._

sealed trait Visibility
object Visibility {
  case object Public extends Visibility
  case object Followers extends Visibility
  case object Private extends Visibility
}
