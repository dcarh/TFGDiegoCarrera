
import cats.effect.{IO, ExitCode, IOApp}
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.implicits._
import endpoints.app.ExampleEndpoint

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    
    val routes = ExampleEndpoint.returnFavouritesRoutes

    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(routes.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
