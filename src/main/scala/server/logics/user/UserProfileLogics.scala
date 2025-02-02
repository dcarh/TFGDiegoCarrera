package server.logics.user

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.UserProfile
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object UserProfileLogics {

  val getUserProfile: UserId => IO[Either[UserError, UserProfile]] =
    userId => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.profile)
        
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
    
  val editUserProfile: ((UserId, UserProfile)) => IO[Either[UserError, UserProfile]] =
    (userId, updatedUserProfileData) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val updatedUser = user.copy(
            profile = user.profile.copy(
              username = updatedUserProfileData.username,
              password = updatedUserProfileData.password,
              email = updatedUserProfileData.email,
              biography = updatedUserProfileData.biography,
              location = updatedUserProfileData.location
            )
          )
          UserRepository.put(userId, updatedUser)
          Right(updatedUser.profile)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
