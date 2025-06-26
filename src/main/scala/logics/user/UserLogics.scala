package logics.user

import cats.effect.IO
import memory.repositories.UserRepository
import domain.app.user.{User, UserFavourites, UserProfile}
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions

object UserLogics {

  private val applyProfileToUser: (User, UserProfile) => Either[UserError, User] =
    (user, userProfile) =>
      val allUsers                = UserRepository.getAll
      val profileWithSameEmail    = allUsers.exists(_.profile.email == userProfile.email)
      val profileWithSameUsername = allUsers.exists(_.profile.username == userProfile.username)

      if profileWithSameEmail then Left(Conflict("User with same email already exists"))
      else
        if profileWithSameUsername then Left(Conflict("User with same username already exists"))
        else
          val updatedUser = user.copy(
            profile = user.profile.copy(
              username  = userProfile.username,
              password  = userProfile.password,
              email     = userProfile.email,
              biography = userProfile.biography,
              location  = userProfile.location
            )
          )

          UserRepository.put(updatedUser.id, updatedUser)
          Right(updatedUser)

  val getAllUsers: Option[String] => IO[Either[UserError, List[User]]] = {
    sortByOption =>
      IO.pure {
        val users = UserRepository.getAll

        val sortedUsers = sortByOption match
          case Some("least_popular") => Right(users.sortBy(_.followersIds.length))
          case Some("most_popular")  => Right(users.sortBy(_.followersIds.length).reverse)
          case Some(unknown)         => Left(BadRequest(s"Invalid sorting parameter: $unknown"))
          case None                  => Right(users)

        sortedUsers

      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getUser: UserId => IO[Either[UserError, User]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getProfile: UserId => IO[Either[UserError, UserProfile]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.profile)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createUser: ((UserId, UserProfile)) => IO[Either[UserError, User]] =
    (newUserId, newUserProfile) => IO.pure {
      UserRepository.get(newUserId) match
        case Some(_)                      => Left(Conflict(s"User with ID ${newUserId.value} already exists"))
        case None if newUserId.value <= 0 => Left(BadRequest("Invalid user ID"))
        case None                         =>
          val emptyList = List()
          val newUser = User(
            id                 = newUserId,
            profile            = UserProfile(username = "", password = "", email = "", biography = "", location = ""),
            favourites         = UserFavourites(movieId = None, tvShowId = None, videogameId = None, bookId = None),
            completedMediaIds  = emptyList,
            pendingMediaIds    = emptyList,
            inProgressMediaIds = emptyList,
            onHoldMediaIds     = emptyList,
            droppedMediaIds    = emptyList,
            mediaListsIds      = emptyList,
            entriesIds         = emptyList,
            reviewsIds         = emptyList,
            ratingsIds         = emptyList,
            likesIds           = emptyList,
            repliesIds         = emptyList,
            followingIds       = emptyList,
            followersIds       = emptyList,
            blockedIds         = emptyList,
            chatsIds           = emptyList,
            archivedChatsIds   = emptyList
          )

          applyProfileToUser(newUser, newUserProfile)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editUser: ((UserId, UserProfile)) => IO[Either[UserError, User]] =
    (userId, updatedUserProfileData) => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => applyProfileToUser(user, updatedUserProfileData)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteUser: UserId => IO[Either[UserError, Unit]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          UserRepository.delete(userId) 
          Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
