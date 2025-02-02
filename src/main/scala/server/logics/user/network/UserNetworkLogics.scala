package server.logics.user.network

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions.{getUser, getBothUsers}

object UserNetworkLogics {

  val getFollowers: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.followers)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getFollowing: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.following)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getBlocked: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.blocked)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val followUser: ((UserId, UserId)) => IO[Either[UserError, (List[UserId], List[UserId])]] =
    (userId, followedUserId) => IO {
      getBothUsers(userId, followedUserId) match
        case Left(error) => Left(error)
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

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unfollowUser: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unfollowedUserId) => IO {
      getBothUsers(userId, unfollowedUserId) match
        case Left(error) => Left(error)
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
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val blockUser: ((UserId, UserId)) => IO[Either[UserError, List[UserId]]] =
    (userId, blockedUserId) => IO {
      getBothUsers(userId, blockedUserId) match
        case Left(error) => Left(error)
        case Right(user, blockedUser) =>
          if user.blocked.contains(blockedUserId) then
            Left(Conflict("User already blocked"))
          else
            val updatedUser = user.copy(
              blocked = blockedUserId :: user.blocked
            )
            UserRepository.put(userId, updatedUser)
            Right(updatedUser.blocked)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unblockUser: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unblockedUserId) => IO {
      getBothUsers(userId, unblockedUserId) match
        case Left(error) => Left(error)
        case Right(user, unblockedUser) =>
          if user.blocked.contains(unblockedUserId) then
            val updatedUser = user.copy(
              blocked = user.blocked.filterNot(_ == unblockedUserId)
            )
            UserRepository.put(userId, updatedUser)
            Right(())
          else
            Left(BadRequest("The user specified wasn't blocked"))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
