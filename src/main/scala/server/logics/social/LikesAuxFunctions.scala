package server.logics.social

import memory.repositories.{MediaListRepository, ReplyRepository, ReviewRepository, UserRepository}
import modelClasses.app.social.{Like, MediaList, Reply, Review}
import modelClasses.app.user.User
import modelClasses.errors.UserError.{BadRequest, UserError}

object LikesAuxFunctions {

  val addNewLikeToUser: (User, Like) => Either[UserError, User] =
    (user, like) =>
      if !user.likesIds.contains(like.id) then
        val updatedUser = user.copy(
          likesIds = like.id :: user.likesIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(updatedUser)

      else
        Left(BadRequest("The user with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromUser: (User, Like) => Either[UserError, User] =
    (user, like) =>
      if user.likesIds.contains(like.id) then
        val updatedUser = user.copy(
          likesIds = user.likesIds.filterNot(_ == like.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the like doesn't own the like"))

  val addNewLikeToMediaList: (MediaList, Like) => Either[UserError, MediaList] =
    (mediaList, like) =>
      if !mediaList.likesIds.contains(like.id) then
        val updatedMediaList = mediaList.copy(
          likesIds = like.id :: mediaList.likesIds
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(updatedMediaList)

      else
        Left(BadRequest("The media list with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromMediaList: (MediaList, Like) => Either[UserError, MediaList] =
    (mediaList, like) =>
      if mediaList.likesIds.contains(like.id) then
        val updatedMediaList = mediaList.copy(
          likesIds = mediaList.likesIds.filterNot(_ == like.id)
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(mediaList)

      else
        Left(BadRequest("The media list with the ID stored in the like doesn't own the like"))

  val addNewLikeToReview: (Review, Like) => Either[UserError, Review] =
    (review, like) =>
      if !review.likesIds.contains(like.id) then
        val updatedReview = review.copy(
          likesIds = like.id :: review.likesIds
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromReview: (Review, Like) => Either[UserError, Review] =
    (review, like) =>
      if review.likesIds.contains(like.id) then
        val updatedReview = review.copy(
          likesIds = review.likesIds.filterNot(_ == like.id)
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the like doesn't own the like"))

  val addNewLikeToReply: (Reply, Like) => Either[UserError, Reply] =
    (reply, like) =>
      if !reply.likesIds.contains(like.id) then
        val updatedReply = reply.copy(
          likesIds = like.id :: reply.likesIds
        )
        ReplyRepository.put(updatedReply.id, updatedReply)
        Right(updatedReply)

      else
        Left(BadRequest("The reply with the ID stored in the like already has a like with the same ID"))

  val removeLikeFromReply: (Reply, Like) => Either[UserError, Reply] =
    (reply, like) =>
      if reply.likesIds.contains(like.id) then
        val updatedReply = reply.copy(
          likesIds = reply.likesIds.filterNot(_ == like.id)
        )
        ReplyRepository.put(updatedReply.id, updatedReply)
        Right(updatedReply)

      else
        Left(BadRequest("The reply with the ID stored in the like doesn't own the like"))

}
