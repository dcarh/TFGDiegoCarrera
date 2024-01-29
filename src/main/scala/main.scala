import sttp.tapir.model.UsernamePassword

object main {

  def main(args: Array[String]): Unit = {
    val tapirTest: pruebaEndpointsTapir = pruebaEndpointsTapir()

    val movieOverview = tapirTest.newMovie.overview

    println(movieOverview)

    case class User(
                     id: Long,
                     email: String,
                     usernamePassword: UsernamePassword
                   )

    val user1: User = User(727891, "d.carrerah.2019@alumnos.urjc.es", UsernamePassword("dcarrerah", Some("6756897845563")))

    println(user1.usernamePassword.username + ":" + user1.usernamePassword.password.get)

  }

}
