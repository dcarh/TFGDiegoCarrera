package server.logics.social

import memory.repositories.{MediaListRepository, ReplyRepository, ReviewRepository, UserRepository}
import modelClasses.app.social.{MediaList, Reply, Review}
import modelClasses.app.user.User
import modelClasses.errors.UserError.{BadRequest, UserError}

object RepliesAuxFunctions {

  val addNewReplyToUser: (User, Reply) => Either[UserError, User] =
    (user, reply) =>
      if !user.repliesIds.contains(reply.id) then
        val updatedUser = user.copy(
          repliesIds = reply.id :: user.repliesIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply already has an reply with the same ID"))

  val updateUserFromReply: (User, Reply) => Either[UserError, User] =
    (user, reply) =>
      if user.repliesIds.contains(reply.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromUser: (User, Reply) => Either[UserError, User] =
    (user, reply) =>
      if user.repliesIds.contains(reply.id) then
        val updatedUser = user.copy(
          repliesIds = user.repliesIds.filterNot(_ == reply.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply doesn't own the reply"))

  val addNewReplyToMediaList: (MediaList, Reply) => Either[UserError, MediaList] =
    (mediaList, reply) =>
      if !mediaList.repliesIds.contains(reply.id) then
        val updatedMediaList = mediaList.copy(
          repliesIds = reply.id :: mediaList.repliesIds
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(updatedMediaList)

      else
        Left(BadRequest("The media list with the ID stored in the reply already has a reply with the same ID"))

  val updateMediaListFromReply: (MediaList, Reply) => Either[UserError, MediaList] =
    (mediaList, reply) =>
      if mediaList.repliesIds.contains(reply.id) then
        Right(mediaList)

      else
        Left(BadRequest("The media list with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromMediaList: (MediaList, Reply) => Either[UserError, MediaList] =
    (mediaList, reply) =>
      if mediaList.repliesIds.contains(reply.id) then
        val updatedMediaList = mediaList.copy(
          repliesIds = mediaList.repliesIds.filterNot(_ == reply.id)
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(updatedMediaList)

      else
        Left(BadRequest("The media list with the ID stored in the reply doesn't own the reply"))

  val addNewReplyToReview: (Review, Reply) => Either[UserError, Review] =
    (review, reply) =>
      if !review.likesIds.contains(reply.id) then
        val updatedReview = review.copy(
          repliesIds = reply.id :: review.repliesIds
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the reply already has a reply with the same ID"))

  val updateReviewFromReply: (Review, Reply) => Either[UserError, Review] =
    (review, reply) =>
      if review.repliesIds.contains(reply.id) then
        Right(review)

      else
        Left(BadRequest("The review with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromReview: (Review, Reply) => Either[UserError, Review] =
    (review, reply) =>
      if review.repliesIds.contains(reply.id) then
        val updatedReview = review.copy(
          repliesIds = review.repliesIds.filterNot(_ == reply.id)
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the reply doesn't own the reply"))

  val addNewReplyToReply: (Reply, Reply) => Either[UserError, Reply] =
    (replied, newReply) =>
      if !replied.likesIds.contains(newReply.id) then
        val updatedReplied = replied.copy(
          repliesIds = newReply.id :: replied.repliesIds
        )
        ReplyRepository.put(updatedReplied.id, updatedReplied)
        Right(updatedReplied)

      else
        Left(BadRequest("The reply with the ID stored in the reply already has a reply with the same ID"))

  val updateReplyFromReply: (Reply, Reply) => Either[UserError, Reply] =
    (updatedReply, reply) =>
      if updatedReply.repliesIds.contains(reply.id) then
        Right(updatedReply)

      else
        Left(BadRequest("The reply with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromReply: (Reply, Reply) => Either[UserError, Reply] =
    (repliedReply, removedReply) =>
      if repliedReply.repliesIds.contains(removedReply.id) then
        val updatedReplied = repliedReply.copy(
          repliesIds = repliedReply.repliesIds.filterNot(_ == removedReply.id)
        )
        ReplyRepository.put(updatedReplied.id, updatedReplied)
        Right(updatedReplied)

      else
        Left(BadRequest("The reply with the ID stored in the reply doesn't own the reply"))

}
