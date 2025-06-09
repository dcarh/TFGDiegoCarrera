package routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.LikesEndpoints
import logics.social.LikesLogics

object LikesRoutes {

  private val getLike: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(LikesEndpoints.getLike.serverLogic(LikesLogics.getLike))

  private val createLike: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(LikesEndpoints.createLike.serverLogic(LikesLogics.createLike))

  private val deleteLike: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(LikesEndpoints.deleteLike.serverLogic(LikesLogics.deleteLike))

  val likesRoutes: HttpRoutes[IO] =
    getLike      <+>
      createLike <+>
      deleteLike

}
