package server.logics.social

import cats.effect.IO
import dummies.repositories.{LikeRepository, UserRepository}

import modelClasses.app.social.Like
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.LikeId
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object LikesLogics {

  private val addNewLikeToUser: (User, Like) => Either[UserError, User] =
    (user, like) =>
      if !user.likes.contains(like.id) then
        val updatedUser = user.copy(
          likes = like.id :: user.likes
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the like already has an like with the same ID"))

  private val removeLikeFromUser: (User, Like) => Either[UserError, User] =
    (user, like) =>
      if user.likes.contains(like.id) then
        val updatedUser = user.copy(
          likes = user.likes.filterNot(_ == like.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the like doesn't own the like"))

  val getLike: LikeId => IO[Either[UserError, Like]] =
    likeId => IO {
      CommonFunctions.getLike(likeId) match 
        case Left(error) => Left(error)
        case Right(like) => Right(like)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createLike: Like => IO[Either[UserError, Like]] =
    newLike => IO {
      LikeRepository.get(newLike.id) match
        case Some(_) => Left(Conflict(s"Like with ID ${newLike.id.value} already exists"))
        case None if newLike.id.value <= 0 => Left(BadRequest("Invalid like ID"))
        case None  =>
          CommonFunctions.getUserAndApply(newLike.userId)(newLike, addNewLikeToUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              LikeRepository.put(newLike.id, newLike)
              Right(newLike)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteLike: LikeId => IO[Either[UserError, Unit]] =
    likeId => IO {
      CommonFunctions.getLike(likeId) match
        case Left(error) => Left(error)
        case Right(like) =>
          CommonFunctions.getUserAndApply(like.userId)(like, removeLikeFromUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              LikeRepository.delete(like.id)
              Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
