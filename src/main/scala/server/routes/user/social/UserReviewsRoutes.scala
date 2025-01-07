package server.routes.user.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserReviewsEndpoints
import server.logics.user.social.UserReviewsLogics

object UserReviewsRoutes {

  private val getUserReviews: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserReviewsEndpoints.getUserReviews.serverLogic(UserReviewsLogics.getUserReviews))

  val userReviewsRoutes: HttpRoutes[IO] =
    getUserReviews

}
