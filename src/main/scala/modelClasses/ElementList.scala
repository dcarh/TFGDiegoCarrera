package modelClasses

case class ElementList(
                      id: ElementList.Id,
                      userId: User.Id,
                      elementsType: String,
                      elementsIds: Seq[Element.Id],
                      likesIds: Seq[Like.Id],
                      commentsIds: Seq[Comment.Id]
                      ) extends AppElement
                      
// TODO: Si no voy a hacer persistencia para los elementos obtenidos de APIs, no tiene sentido que haga los companion
//  objects para ellos

object ElementList {
  type Id = Long
}