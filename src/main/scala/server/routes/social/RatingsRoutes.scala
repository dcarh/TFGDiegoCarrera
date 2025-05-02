package server.routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.RatingsEndpoints
import server.logics.social.RatingsLogics

object RatingsRoutes {

  private val getRating: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RatingsEndpoints.getRating.serverLogic(RatingsLogics.getRating))

  private val createRating: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RatingsEndpoints.createRating.serverLogic(RatingsLogics.createRating))

  private val editRating: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RatingsEndpoints.editRating.serverLogic(RatingsLogics.editRating))

  private val deleteRating: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RatingsEndpoints.deleteRating.serverLogic(RatingsLogics.deleteRating))

  val ratingsRoutes: HttpRoutes[IO] =
    getRating      <+>
      createRating <+>
      editRating   <+>
      deleteRating

}
