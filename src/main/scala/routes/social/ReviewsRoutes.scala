package routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.ReviewsEndpoints
import logics.social.ReviewsLogics

object ReviewsRoutes {

  private val getAllReviews: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ReviewsEndpoints.getAllReviews.serverLogic(ReviewsLogics.getAllReviews))

  private val getReview: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ReviewsEndpoints.getReview.serverLogic(ReviewsLogics.getReview))

  private val createReview: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ReviewsEndpoints.createReview.serverLogic(ReviewsLogics.createReview))

  private val editReview: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ReviewsEndpoints.editReview.serverLogic(ReviewsLogics.editReview))

  private val deleteReview: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ReviewsEndpoints.deleteReview.serverLogic(ReviewsLogics.deleteReview))

  val reviewsRoutes: HttpRoutes[IO] =
    getAllReviews  <+>
      getReview    <+>
      createReview <+>
      editReview   <+>
      deleteReview

}
