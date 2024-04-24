package modelClasses

// case class MediaContentList(
//                       id: MediaList.Id,
//                       userId: User.Id,
//                       elementsType: String,
//                       elementsIds: List[MediaContent.Id],
//                       likesIds: List[Like.Id],
//                       commentsIds: List[Comment.Id]
//                       ) extends UserContent
//                       
// // TODO: Si no voy a hacer persistencia para los elementos obtenidos de APIs, no tiene sentido que haga los companion
// //  objects para ellos
// 
// object MediaContentList {
//   type Id = Long
// }