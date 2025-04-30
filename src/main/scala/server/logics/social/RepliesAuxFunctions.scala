package server.logics.social

import dummies.repositories.{MediaListRepository, ReplyRepository, ReviewRepository, UserRepository}
import modelClasses.app.social.{MediaList, Reply, Review}
import modelClasses.app.user.User
import modelClasses.errors.UserError.{BadRequest, UserError}

object RepliesAuxFunctions {

  val addNewReplyToUser: (User, Reply) => Either[UserError, User] =
    (user, reply) =>
      if !user.replies.contains(reply.id) then
        val updatedUser = user.copy(
          replies = reply.id :: user.replies
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply already has an reply with the same ID"))

  val updateUserFromReply: (User, Reply) => Either[UserError, User] =
    (user, reply) =>
      if user.replies.contains(reply.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromUser: (User, Reply) => Either[UserError, User] =
    (user, reply) =>
      if user.replies.contains(reply.id) then
        val updatedUser = user.copy(
          replies = user.replies.filterNot(_ == reply.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply doesn't own the reply"))

  val addNewReplyToMediaList: (MediaList, Reply) => Either[UserError, MediaList] =
    (mediaList, reply) =>
      if !mediaList.replies.contains(reply.id) then
        val updatedMediaList = mediaList.copy(
          replies = reply.id :: mediaList.replies
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(updatedMediaList)

      else
        Left(BadRequest("The media list with the ID stored in the reply already has a reply with the same ID"))

  val updateMediaListFromReply: (MediaList, Reply) => Either[UserError, MediaList] =
    (mediaList, reply) =>
      if mediaList.replies.contains(reply.id) then
        Right(mediaList)

      else
        Left(BadRequest("The media list with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromMediaList: (MediaList, Reply) => Either[UserError, MediaList] =
    (mediaList, reply) =>
      if mediaList.replies.contains(reply.id) then
        val updatedMediaList = mediaList.copy(
          replies = mediaList.replies.filterNot(_ == reply.id)
        )
        MediaListRepository.put(updatedMediaList.id, updatedMediaList)
        Right(updatedMediaList)

      else
        Left(BadRequest("The media list with the ID stored in the reply doesn't own the reply"))

  val addNewReplyToReview: (Review, Reply) => Either[UserError, Review] =
    (review, reply) =>
      if !review.likes.contains(reply.id) then
        val updatedReview = review.copy(
          replies = reply.id :: review.replies
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the reply already has a reply with the same ID"))

  val updateReviewFromReply: (Review, Reply) => Either[UserError, Review] =
    (review, reply) =>
      if review.replies.contains(reply.id) then
        Right(review)

      else
        Left(BadRequest("The review with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromReview: (Review, Reply) => Either[UserError, Review] =
    (review, reply) =>
      if review.replies.contains(reply.id) then
        val updatedReview = review.copy(
          replies = review.replies.filterNot(_ == reply.id)
        )
        ReviewRepository.put(updatedReview.id, updatedReview)
        Right(updatedReview)

      else
        Left(BadRequest("The review with the ID stored in the reply doesn't own the reply"))

  val addNewReplyToReply: (Reply, Reply) => Either[UserError, Reply] =
    (replied, newReply) =>
      if !replied.likes.contains(newReply.id) then
        val updatedReplied = replied.copy(
          replies = newReply.id :: replied.replies
        )
        ReplyRepository.put(updatedReplied.id, updatedReplied)
        Right(updatedReplied)

      else
        Left(BadRequest("The reply with the ID stored in the reply already has a reply with the same ID"))

  val updateReplyFromReply: (Reply, Reply) => Either[UserError, Reply] =
    (updatedReply, reply) =>
      if updatedReply.replies.contains(reply.id) then
        Right(updatedReply)

      else
        Left(BadRequest("The reply with the ID stored in the reply doesn't own the reply"))

  val removeReplyFromReply: (Reply, Reply) => Either[UserError, Reply] =
    (repliedReply, removedReply) =>
      if repliedReply.replies.contains(removedReply.id) then
        val updatedReplied = repliedReply.copy(
          replies = repliedReply.replies.filterNot(_ == removedReply.id)
        )
        ReplyRepository.put(updatedReplied.id, updatedReplied)
        Right(updatedReplied)

      else
        Left(BadRequest("The reply with the ID stored in the reply doesn't own the reply"))

}
