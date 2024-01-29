package model
import sttp.tapir.model.UsernamePassword

case class User(
               id: Long,
               email: String,
               usernamePassword: UsernamePassword
               )
