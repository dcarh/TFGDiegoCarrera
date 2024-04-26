package modelClasses

case class UserProfile(
                      username : String,
                      password : String,
                      email    : String,
                      biography: String,
                      location : String   // TODO: Cambiar por Location
                      )
