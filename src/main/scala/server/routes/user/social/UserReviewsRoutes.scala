package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserReviewsEndpoints.*
import server.logics.user.social.UserReviewsLogics.*

object UserReviewsRoutes {

  val getUserReviewsRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getUserReviews.serverLogic(getUserReviewsLogic))

}
