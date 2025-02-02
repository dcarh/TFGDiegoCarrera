package server.logics.user.network

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserNetworkLogics {

  private def getUserFromRepository(userId: UserId): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) => Right(user)

      case None if userId.value <= 0 =>
        Left(BadRequest("Invalid user ID"))

      case None =>
        Left(NotFound(s"User with ID ${userId.value} not found"))

  private def checkBothUsersExist(userId1: UserId, userId2: UserId): Either[UserError, (User, User)] =
    getUserFromRepository(userId1) match
      case Right(user1) =>
        getUserFromRepository(userId2) match
          case Right(user2) =>

            Right(user1, user2)

          case Left(error) =>
            Left(error)

      case Left(error) =>
        Left(error)

  val getFollowers: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      getUserFromRepository(userId) match
        case Right(user) =>
          Right(user.followers)

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getFollowing: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      getUserFromRepository(userId) match
        case Right(user) =>
          Right(user.following)

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getBlocked: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      getUserFromRepository(userId) match
        case Right(user) =>
          Right(user.blocked)

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val followUser: ((UserId, UserId)) => IO[Either[UserError, (List[UserId], List[UserId])]] =
    (userId, followedUserId) => IO {
      checkBothUsersExist(userId, followedUserId) match
        case Right(user, followedUser) =>
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

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unfollowUser: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unfollowedUserId) => IO {
      checkBothUsersExist(userId, unfollowedUserId) match
        case Right(user, unfollowedUser) =>
          if user.following.contains(unfollowedUserId) then
            val updatedUser = user.copy(
              following = user.following.filterNot(_ == unfollowedUserId)
            )
            val updatedUnfollowedUser = unfollowedUser.copy(
              followers = unfollowedUser.followers.filterNot(_ == userId)
            )
            UserRepository.put(userId, updatedUser)
            UserRepository.put(unfollowedUserId, updatedUnfollowedUser)
            Right(())
          else
            Left(BadRequest("Didn't already follow the user specified"))

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val blockUser: ((UserId, UserId)) => IO[Either[UserError, List[UserId]]] =
    (userId, blockedUserId) => IO {
      checkBothUsersExist(userId, blockedUserId) match
        case Right(user, blockedUser) =>
          if user.blocked.contains(blockedUserId) then
            Left(Conflict("User already blocked"))
          else
            val updatedUser = user.copy(
              blocked = blockedUserId :: user.blocked
            )
            UserRepository.put(userId, updatedUser)
            Right(updatedUser.blocked)

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unblockUser: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unblockedUserId) => IO {
      checkBothUsersExist(userId, unblockedUserId) match
        case Right(user, unblockedUser) =>
          if user.blocked.contains(unblockedUserId) then
            val updatedUser = user.copy(
              blocked = user.blocked.filterNot(_ == unblockedUserId)
            )
            UserRepository.put(userId, updatedUser)
            Right(())
          else
            Left(BadRequest("The user specified wasn't blocked"))

        case Left(error) =>
          Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
