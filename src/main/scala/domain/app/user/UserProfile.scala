package domain.app.user

case class UserProfile(
                        username : String,
                        password : String,
                        // TODO: usernamePassword: UsernamePassword?
                        email    : String,
                        biography: String,
                        location : String
                      )
