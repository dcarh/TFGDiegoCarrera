package server.logics.user.network

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserNetworkLogics {

  val getFollowersLogic: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.followers)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getFollowingLogic: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      UserRepository.get(userId) match
        case Some(user) =>
          Right(user.following)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getBlockedLogic: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      UserRepository.get(userId) match
        case Some(user) =>
          Right(user.blocked)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val followUserLogic: ((UserId, UserId)) => IO[Either[UserError, (List[UserId], List[UserId])]] =
    (userId, followedUserId) => IO {
      UserRepository.get(userId) match
        case Some(user) =>
          UserRepository.get(followedUserId) match
            case Some(followedUser) =>
              if user.following.contains(followedUserId) then
                Left(Conflict("User already followed"))
              else
                val updatedUser = user.copy(
                  following = followedUserId :: user.following
                )
                val updatedFollowedUser = followedUser.copy(
                  followers = userId :: followedUser.followers
                )
                UserRepository.put(userId, updatedUser)
                UserRepository.put(followedUserId, updatedFollowedUser)
                Right((updatedUser.following, updatedFollowedUser.followers))
            case None if userId.value <= 0 =>
              Left(BadRequest("Invalid followed user ID"))
            case None =>
              Left(NotFound(s"Followed user with ID ${userId.value} not found"))
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unfollowUserLogic: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unfollowedUserId) => IO {
      UserRepository.get(userId) match
        case Some(user) =>
          UserRepository.get(unfollowedUserId) match
            case Some(followedUser) =>
              if user.following.contains(unfollowedUserId) then
                val updatedUser = user.copy(
                  following = user.following.filterNot(_ == unfollowedUserId)
                )
                val updatedFollowedUser = followedUser.copy(
                  followers = followedUser.followers.filterNot(_ == userId)
                )
                UserRepository.put(userId, updatedUser)
                UserRepository.put(unfollowedUserId, updatedFollowedUser)
                Right(())
              else
                Left(BadRequest("Didn't already follow the user specified"))
            case None if userId.value <= 0 =>
              Left(BadRequest("Invalid followed user ID"))
            case None =>
              Left(NotFound(s"Unfollowed user with ID ${userId.value} not found"))
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val blockUserLogic: ((UserId, UserId)) => IO[Either[UserError, List[UserId]]] =
    (userId, blockedUserId) => IO {
      UserRepository.get(userId) match
        case Some(user) =>
          UserRepository.get(blockedUserId) match
            case Some(blockedUser) =>
              if user.blocked.contains(blockedUserId) then
                Left(Conflict("User already blocked"))
              else
                val updatedUser = user.copy(
                  blocked = blockedUserId :: user.blocked
                )
                UserRepository.put(userId, updatedUser)
                Right(updatedUser.blocked)
            case None if userId.value <= 0 =>
              Left(BadRequest("Invalid blocked user ID"))
            case None =>
              Left(NotFound(s"Blocked user with ID ${userId.value} not found"))
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unblockUserLogic: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unblockedUserId) => IO {
      UserRepository.get(userId) match
        case Some(user) =>
          UserRepository.get(unblockedUserId) match
            case Some(blockedUser) =>
              if user.blocked.contains(unblockedUserId) then
                val updatedUser = user.copy(
                  blocked = user.blocked.filterNot(_ == unblockedUserId)
                )
                UserRepository.put(userId, updatedUser)
                Right(())
              else
                Left(BadRequest("The user specified wasn't blocked"))
            case None if userId.value <= 0 =>
              Left(BadRequest("Invalid unblocked user ID"))
            case None =>
              Left(NotFound(s"Unblocked user with ID ${userId.value} not found"))
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
