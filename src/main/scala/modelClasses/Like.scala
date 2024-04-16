package modelClasses

case class Like(
               id: Like.Id,
               elementId: Either[Element.Id, AppElement.Id],
               userId: User.Id
               )
 object Like {
   type Id = Long
 }