package domain.errors.UserError

sealed trait UserError
case class BadRequest(what: String) extends UserError
case class NotFound(what: String) extends UserError
case class Unauthorized(realm: String) extends UserError
case class Conflict(what: String) extends UserError
case class Unknown(code: Int, msg: String) extends UserError
case class NoContent() extends UserError
//case object NoContent extends UserError
