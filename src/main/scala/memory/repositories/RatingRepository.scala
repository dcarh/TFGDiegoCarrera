package memory.repositories

import memory.ids.RatingIds
import memory.objects.Ratings
import modelClasses.app.social.Rating
import modelClasses.ids.Social.RatingId

object RatingRepository {
  private val repository: InMemoryRepository[RatingId, Rating] = InMemoryRepository(
    Map(
      RatingIds.ratingId1 -> Ratings.rating1,
      RatingIds.ratingId2 -> Ratings.rating2,
      RatingIds.ratingId3 -> Ratings.rating3,
      RatingIds.ratingId4 -> Ratings.rating4,
      RatingIds.ratingId5 -> Ratings.rating5,
      RatingIds.ratingId6 -> Ratings.rating6,
      RatingIds.ratingId7 -> Ratings.rating7,
      RatingIds.ratingId8 -> Ratings.rating8,
      RatingIds.ratingId9 -> Ratings.rating9,
      RatingIds.ratingId10 -> Ratings.rating10,
      RatingIds.ratingId11 -> Ratings.rating11,
      RatingIds.ratingId12 -> Ratings.rating12,
      RatingIds.ratingId13 -> Ratings.rating13,
      RatingIds.ratingId14 -> Ratings.rating14
    )
  )

  def get(id: RatingId): Option[Rating] = repository.get(id)
  def getAll: List[Rating] = repository.getAll
  def getMany(ids: List[RatingId]) = repository.getMany(ids)
  def put(id: RatingId, value: Rating): String = repository.put(id, value)
  def delete(id: RatingId): String = repository.delete(id)
}
