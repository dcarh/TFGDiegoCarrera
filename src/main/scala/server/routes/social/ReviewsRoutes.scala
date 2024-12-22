package server.routes.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.ReviewsEndpoints.*
import server.logics.social.ReviewsLogics.*

object ReviewsRoutes {

  val getAllReviewsRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getAllReviewsEndpoint.serverLogic(getAllReviewsLogic))

  val getReviewRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getReviewEndpoint.serverLogic(getReviewLogic))

  val createReviewRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createReviewEndpoint.serverLogic(createReviewLogic))

  val editReviewRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editReviewEndpoint.serverLogic(editReviewLogic))

  val deleteReviewRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteReviewEndpoint.serverLogic(deleteReviewLogic))

}
