import cats.effect.*
import clients.IGDBClient
import endpoints.igdb.Videogames
import modelClasses.ids.Media.VideogameId

object GetIGDBRequestExample extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    IGDBClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, "Doom").flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }
  }

}
