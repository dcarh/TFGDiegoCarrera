
import cats.effect.{IO, ExitCode, IOApp}
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.implicits._
import api.ExampleEndpoint

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val endpoint = new ExampleEndpoint()

    val routes = endpoint.returnFavouritesRoutes

    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(routes.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
