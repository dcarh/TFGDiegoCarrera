import cats.effect.*
import clients.IGDBClient
import endpoints.igdb.Videogames
import modelClasses.app.media.IDs.VideogameId

object GetIGDBRequestExample extends IOApp {

  private val igdbClient = IGDBClient()

  override def run(args: List[String]): IO[ExitCode] = {
    igdbClient.executeRequest(Videogames.requestVideogameEndpoint, VideogameId(19686)).flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }
  }

}
