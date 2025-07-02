package domain.app.user

case class UserProfile(
                        username : String,
                        password : String,
                        email    : String,
                        biography: String,
                        location : String
                      )
