import cats.effect.{IO, ExitCode, IOApp}
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.implicits._
import routes.HttpRoutes.httpRoutes

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(httpRoutes.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
