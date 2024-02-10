package model
import sttp.tapir.model.UsernamePassword

case class User(
               id: Int,
               email: String,
               usernamePassword: UsernamePassword,
               settings: Settings
               )
