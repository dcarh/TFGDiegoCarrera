package logics.social

import cats.effect.IO
import memory.repositories.ReviewRepository
import domain.app.social.Review
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Media.{BookId, MovieId, TvEpisodeNumber, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.ReviewId
import logics.functions.{CommonFunctions, ReviewsAuxFunctions}

object ReviewsLogics {


  val getAllReviews: ((Option[String], Option[List[String]])) => IO[Either[UserError, List[Review]]] = {
    (sortByOption, categoryOption) => IO.pure {
      val reviews = ReviewRepository.getAll

      val filteredReviews = categoryOption match
        case None             => reviews
        case Some(categories) =>
          reviews.filter(
            review => review.reviewedMediaId match
              case _: MovieId                                           => categories.contains("movie")
              case _: TvShowId                                          => categories.contains("tv_show")
              case (_: TvShowId, _: TvSeasonNumber)                     => categories.contains("season")
              case (_: TvShowId, _: TvSeasonNumber, _: TvEpisodeNumber) => categories.contains("episode")
              case _: VideogameId                                       => categories.contains("videogame")
              case _: BookId                                            => categories.contains("book")
          )
      
      val sortedReviews = sortByOption match {
        case Some("least_liked")   => Right(filteredReviews.sortBy(_.likesIds.size))
        case Some("most_liked")    => Right(filteredReviews.sortBy(_.likesIds.size).reverse)
        case Some("least_replied") => Right(filteredReviews.sortBy(_.repliesIds.size))
        case Some("most_replied")  => Right(filteredReviews.sortBy(_.repliesIds.size).reverse)
        case Some(unknown)         => Left(BadRequest(s"Invalid sorting parameter: $unknown"))
        case None                  => Right(filteredReviews)
      }
      sortedReviews
        
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
  }

  val getReview: ReviewId => IO[Either[UserError, Review]] =
    reviewId => IO.pure {
      CommonFunctions.getReview(reviewId) match
        case Left(error)   => Left(error)
        case Right(review) => Right(review)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReview: Review => IO[Either[UserError, Review]] =
    newReview => IO.pure {
      ReviewRepository.get(newReview.id) match
        case Some(_)                         => Left(Conflict(s"Review with ID ${newReview.id.value} already exists"))
        case None if newReview.id.value <= 0 => Left(BadRequest("Invalid review ID"))
        case None                            =>
          CommonFunctions.getUserAndApply(newReview.userId)(newReview, ReviewsAuxFunctions.addNewReviewToUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              ReviewRepository.put(newReview.id, newReview)
              Right(newReview)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReview: ((ReviewId, Review)) => IO[Either[UserError, Review]] =
    (reviewId, updatedReview) => IO.pure {
      if (reviewId.value != updatedReview.id.value)
        Left(BadRequest("Review ID in path and updated review ID did not match"))
      else
        CommonFunctions.getReview(reviewId) match
          case Left(error)           => Left(error)
          case Right(existingReview) =>
            CommonFunctions.getUserAndApply(existingReview.userId)(existingReview, ReviewsAuxFunctions.updateUserFromReview) match
              case Left(error)  => Left(error)
              case Right(value) =>
                ReviewRepository.put(reviewId, updatedReview)
                Right(updatedReview)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReview: ReviewId => IO[Either[UserError, Unit]] =
    reviewId => IO.pure {
      CommonFunctions.getReview(reviewId) match
        case Left(error)   => Left(error)
        case Right(review) =>
          CommonFunctions.getUserAndApply(review.userId)(review, ReviewsAuxFunctions.removeReviewFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              ReviewRepository.delete(review.id)
              Right(())

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
