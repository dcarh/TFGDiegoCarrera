package logics.user.network

import cats.effect.IO
import memory.repositories.UserRepository
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.User.UserId

import logics.functions.CommonFunctions.{getUser, getBothUsers}

object UserNetworkLogics {

  val getFollowers: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO.pure {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.followersIds)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getFollowing: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO.pure {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.followingIds)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getBlocked: UserId => IO[Either[UserError, List[UserId]]] =
    userId => IO.pure {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.blockedIds)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val followUser: ((UserId, UserId)) => IO[Either[UserError, (List[UserId], List[UserId])]] =
    (userId, followedUserId) => IO.pure {
      getBothUsers(userId, followedUserId) match
        case Left(error)               => Left(error)
        case Right(user, followedUser) =>
          if user.followingIds.contains(followedUserId) then
            Left(Conflict("User already followed"))
          else
            val updatedUser = user.copy(
              followingIds = followedUserId :: user.followingIds
            )
            val updatedFollowedUser = followedUser.copy(
              followersIds = userId :: followedUser.followersIds
            )
            UserRepository.put(userId, updatedUser)
            UserRepository.put(followedUserId, updatedFollowedUser)
            Right((updatedUser.followingIds, updatedFollowedUser.followersIds))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unfollowUser: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unfollowedUserId) => IO.pure {
      getBothUsers(userId, unfollowedUserId) match
        case Left(error)                 => Left(error)
        case Right(user, unfollowedUser) =>
          if user.followingIds.contains(unfollowedUserId) then
            val updatedUser = user.copy(
              followingIds = user.followingIds.filterNot(_ == unfollowedUserId)
            )
            val updatedUnfollowedUser = unfollowedUser.copy(
              followersIds = unfollowedUser.followersIds.filterNot(_ == userId)
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
    (userId, blockedUserId) => IO.pure {
      getBothUsers(userId, blockedUserId) match
        case Left(error)              => Left(error)
        case Right(user, blockedUser) =>
          if user.blockedIds.contains(blockedUserId) then
            Left(Conflict("User already blocked"))
          else
            val updatedUser = user.copy(
              blockedIds = blockedUserId :: user.blockedIds
            )
            UserRepository.put(userId, updatedUser)
            Right(updatedUser.blockedIds)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val unblockUser: ((UserId, UserId)) => IO[Either[UserError, Unit]] =
    (userId, unblockedUserId) => IO.pure {
      getBothUsers(userId, unblockedUserId) match
        case Left(error)                => Left(error)
        case Right(user, unblockedUser) =>
          if user.blockedIds.contains(unblockedUserId) then
            val updatedUser = user.copy(
              blockedIds = user.blockedIds.filterNot(_ == unblockedUserId)
            )
            UserRepository.put(userId, updatedUser)
            Right(())
          else
            Left(BadRequest("The user specified wasn't blocked"))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
