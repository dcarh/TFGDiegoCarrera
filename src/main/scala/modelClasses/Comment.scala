package modelClasses

case class Comment(
                  id: Comment.Id,
                  userId: User.Id,
                  comment: String,
                  likesIds: Seq[Like.Id],
                  objectCommentedId: Either[ElementList.Id, Review.Id]
                  ) extends AppElement

// TODO: objectCommentedId -> Elegir definitivamente qué tipos de elementos pueden ser. Elegir también cómo implementar
//  esos elementos. ¿case class Element? ¿sealed trait? ¿Distintos tipos de Comment que heredan de Comment todos
//  excepto objectCommentedId?

object Comment {
  type Id = Long
}