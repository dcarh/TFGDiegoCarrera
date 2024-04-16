package modelClasses

case class Book(
               id: Book.Id,
               title: String,
               authors: Seq[String],
               description: String,
               categories: Seq[String],

               likes: Seq[Like.Id],
               reviews: Seq[Review.Id],
               averageRating: Double,
               ratings: Long,
               lists: Seq[ElementList.Id],
               completed: Long,
               inProgress: Long,
               pending: Long,
               abandoned: Long
               ) extends Element

object Book {
  type Id = Long
}
