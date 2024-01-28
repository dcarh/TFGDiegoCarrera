object main {

  def main(args: Array[String]): Unit = {
    val tapirTest: pruebaEndpointsTapir = pruebaEndpointsTapir()

    val movieOverview = tapirTest.newMovie.overview

    println(movieOverview )

  }

}
