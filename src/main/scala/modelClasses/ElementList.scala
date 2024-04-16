package modelClasses

case class ElementList(
                      id: ElementList.Id,
                      userId: User.Id,
                      elementsType: String,
                      elementsIds: List[Element.Id],
                      likesIds: List[Like.Id],
                      commentsIds: List[Comment.Id]
                      ) extends AppElement
                      
// TODO: Si no voy a hacer persistencia para los elementos obtenidos de APIs, no tiene sentido que haga los companion
//  objects para ellos

object ElementList {
  type Id = Long
}