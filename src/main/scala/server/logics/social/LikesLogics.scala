package server.logics.social

import cats.effect.IO
import dummies.repositories.{LikeRepository, UserRepository}

import modelClasses.app.social.Like
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.LikeId
import modelClasses.ids.User.UserId

object LikesLogics {

  private def checkIfUserExistsAndApply(userId: UserId)(likeId: LikeId, f: (User, LikeId) => Either[UserError, User]): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) =>
        f(user, likeId)

      case None if userId.value <= 0 =>
        Left(BadRequest("Invalid like ID"))

      case None =>
        Left(NotFound(s"Like with ID ${userId.value} not found"))

  private val addNewLikeToUser: (User, LikeId) => Either[UserError, User] =
    (user, likeId) =>
      if !user.likes.contains(likeId) then
        val updatedUser = user.copy(
          likes = likeId :: user.likes
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the like already has an like with the same ID"))

  private val removeLikeFromUser: (User, LikeId) => Either[UserError, User] =
    (user, likeId) =>
      if user.likes.contains(likeId) then
        val updatedUser = user.copy(
          likes = user.likes.filterNot(_ == likeId)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the like doesn't own the like"))

  val getLike: LikeId => IO[Either[UserError, Like]] =
    likeId => IO {
      LikeRepository.get(likeId) match 
        case Some(like) =>
          Right(like)

        case None if likeId.value <= 0 =>
          Left(BadRequest("Invalid like ID"))

        case None =>
          Left(NotFound(s"Like with ID ${likeId.value} not found"))
      
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createLike: Like => IO[Either[UserError, Like]] =
    newLike => IO {
      LikeRepository.get(newLike.id) match
        case Some(_) =>
          Left(Conflict(s"Like with ID ${newLike.id.value} already exists"))

        case None if newLike.id.value <= 0 =>
          Left(BadRequest("Invalid like ID"))

        case None  =>
          checkIfUserExistsAndApply(newLike.userId)(newLike.id, addNewLikeToUser) match
            case Right(_) =>
              LikeRepository.put(newLike.id, newLike)
              Right(newLike)

            case Left(error) =>
              Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteLike: LikeId => IO[Either[UserError, Unit]] =
    likeId => IO {
      LikeRepository.get(likeId) match
        case Some(like) =>
          checkIfUserExistsAndApply(like.userId)(like.id, removeLikeFromUser) match
            case Right(_) =>
              LikeRepository.delete(like.id)
              Right(())
            
            case Left(error) => Left(error)
          
        case None if likeId.value <= 0 =>
          Left(BadRequest("Invalid like ID"))

        case None =>
          Left(NotFound(s"Like with ID ${likeId.value} not found"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
