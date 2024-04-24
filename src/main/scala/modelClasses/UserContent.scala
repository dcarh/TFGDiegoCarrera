package modelClasses

import sttp.tapir.generic.auto._

sealed trait UserContent {
  type Id <: Long
}

object UserContent {
  type Id = Long
}

case class MediaContentList(
                        id: MediaContentList.Id,
                        userId: User.Id,
                        elementsType: String,
                        mediaContentsIds: List[MediaContent.Id],
                        likesIds: List[Like.Id],
                        commentsIds: List[Comment.Id]
                      ) extends UserContent

// TODO: Si no voy a hacer persistencia para los elementos obtenidos de APIs, no tiene sentido que haga los companion
//  objects para ellos

object MediaContentList {
  type Id = Long
}

case class Review(
                   id: Review.Id,
                   userId: User.Id,
                   elementId: Int,
                   elementTitle: String,
                   elementType: String,
                   rating: Int,
                   textReview: String,
                   like: Boolean,
                   firstTime: Boolean,
                   completed: Boolean,
                   paused: Boolean,
                   abandoned: Boolean,
                   reviewDate: String,    // TODO: Cambiar a Date y resolver errores que se generan
                   startedDate: String,   // TODO: Cambiar a Date y resolver errores que se generan
                   platform: String,
                   timeSpent: Time,
                   tags: List[String]
                 ) extends UserContent

object Review {
  type Id = Long
}


case class Comment(
                    id: Comment.Id,
                    userId: User.Id,
                    comment: String,
                    likesIds: List[Like.Id],
                    objectCommentedId: Either[MediaContentList.Id, Review.Id]
                  ) extends UserContent

// TODO: objectCommentedId -> Elegir definitivamente qué tipos de elementos pueden ser. Elegir también cómo implementar
//  esos elementos. ¿case class Element? ¿sealed trait? ¿Distintos tipos de Comment que heredan de Comment todos
//  excepto objectCommentedId?

object Comment {
  type Id = Long
}