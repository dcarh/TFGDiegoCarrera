package server.logics.social

import cats.effect.IO
import memory.repositories.{ReviewRepository, UserRepository}

import domain.app.social.Review
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.ReviewId
import domain.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object ReviewsLogics {

  private val addNewReviewToUser: (User, Review) => Either[UserError, User] =
    (user, review) =>
      if !user.reviewsIds.contains(review.id) then
        val updatedUser = user.copy(
          reviewsIds = review.id :: user.reviewsIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the review already has an review with the same ID"))

  private val updateUserFromReview: (User, Review) => Either[UserError, User] =
    (user, review) =>
      if user.reviewsIds.contains(review.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the review doesn't own the review"))

  private val removeReviewFromUser: (User, Review) => Either[UserError, User] =
    (user, review) =>
      if user.reviewsIds.contains(review.id) then
        val updatedUser = user.copy(
          reviewsIds = user.reviewsIds.filterNot(_ == review.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the review doesn't own the review"))


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
              case videogameId: VideogameId                             => categories.contains("videogame")
              case bookId: BookId                                       => categories.contains("book")
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
          CommonFunctions.getUserAndApply(newReview.userId)(newReview, addNewReviewToUser) match
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
            CommonFunctions.getUserAndApply(existingReview.userId)(existingReview, updateUserFromReview) match
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
          CommonFunctions.getUserAndApply(review.userId)(review, removeReviewFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              ReviewRepository.delete(review.id)
              Right(())

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
