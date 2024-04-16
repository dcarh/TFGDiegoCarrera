package modelClasses

case class Book(
               id: Book.Id,
               title: String,
               authors: List[String],
               description: String,
               categories: List[String],

               likes: List[Like.Id],
               reviews: List[Review.Id],
               averageRating: Double,
               ratings: Long,
               lists: List[ElementList.Id],
               completed: Long,
               inProgress: Long,
               pending: Long,
               abandoned: Long
               ) extends Element

object Book {
  type Id = Long
}
