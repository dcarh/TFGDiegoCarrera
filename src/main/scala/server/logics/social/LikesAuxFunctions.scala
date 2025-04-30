package server.logics.social

import dummies.repositories.{MediaListRepository, ReplyRepository, ReviewRepository, UserRepository}
import modelClasses.app.social.{Like, MediaList, Reply, Review}
import modelClasses.app.user.User
import modelClasses.errors.UserError.{BadRequest, UserError}

object LikesAuxFunctions {

  val addNewLikeToUser: (User, Like) => Either[UserError, User] =
    (user, like) =>
      if !user.likes.contains(like.id) then
        val updatedUser = user.copy(
          likes = like.id :: user.likes
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(updatedUser)

      else
        Left(BadRequest("The user with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromUser: (User, Like) => Either[UserError, User] =
    (user, like) =>
      if user.likes.contains(like.id) then
        val updatedUser = user.copy(
          likes = user.likes.filterNot(_ == like.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the like doesn't own the like"))

  val addNewLikeToMediaList: (MediaList, Like) => Either[UserError, MediaList] =
    (mediaList, like) =>
      if !mediaList.likes.contains(like.id) then
        val updatedMediaList = mediaList.copy(
          likes = like.id :: mediaList.likes
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(updatedMediaList)

      else
        Left(BadRequest("The media list with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromMediaList: (MediaList, Like) => Either[UserError, MediaList] =
    (mediaList, like) =>
      if mediaList.likes.contains(like.id) then
        val updatedMediaList = mediaList.copy(
          likes = mediaList.likes.filterNot(_ == like.id)
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(mediaList)

      else
        Left(BadRequest("The media list with the ID stored in the like doesn't own the like"))

  val addNewLikeToReview: (Review, Like) => Either[UserError, Review] =
    (review, like) =>
      if !review.likes.contains(like.id) then
        val updatedReview = review.copy(
          likes = like.id :: review.likes
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromReview: (Review, Like) => Either[UserError, Review] =
    (review, like) =>
      if review.likes.contains(like.id) then
        val updatedReview = review.copy(
          likes = review.likes.filterNot(_ == like.id)
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the like doesn't own the like"))

  val addNewLikeToReply: (Reply, Like) => Either[UserError, Reply] =
    (reply, like) =>
      if !reply.likes.contains(like.id) then
        val updatedReply = reply.copy(
          likes = like.id :: reply.likes
        )
        ReplyRepository.put(updatedReply.id, updatedReply)
        Right(updatedReply)

      else
        Left(BadRequest("The reply with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromReply: (Reply, Like) => Either[UserError, Reply] =
    (reply, like) =>
      if reply.likes.contains(like.id) then
        val updatedReply = reply.copy(
          likes = reply.likes.filterNot(_ == like.id)
        )
        ReplyRepository.put(updatedReply.id, updatedReply)
        Right(updatedReply)

      else
        Left(BadRequest("The reply with the ID stored in the like doesn't own the like"))

}
