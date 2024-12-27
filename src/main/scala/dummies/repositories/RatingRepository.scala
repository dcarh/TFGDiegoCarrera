package dummies.repositories

import dummies.ids.RatingIdDummies
import dummies.objects.RatingDummies
import modelClasses.app.social.Rating
import modelClasses.ids.Social.RatingId

object RatingRepository {
  private val repository: InMemoryRepository[RatingId, Rating] = InMemoryRepository(
    Map(
      RatingIdDummies.ratingId1 -> RatingDummies.rating1,
      RatingIdDummies.ratingId2 -> RatingDummies.rating2,
      RatingIdDummies.ratingId3 -> RatingDummies.rating3,
      RatingIdDummies.ratingId4 -> RatingDummies.rating4,
      RatingIdDummies.ratingId5 -> RatingDummies.rating5,
      RatingIdDummies.ratingId6 -> RatingDummies.rating6,
      RatingIdDummies.ratingId7 -> RatingDummies.rating7,
      RatingIdDummies.ratingId8 -> RatingDummies.rating8,
      RatingIdDummies.ratingId9 -> RatingDummies.rating9,
      RatingIdDummies.ratingId10 -> RatingDummies.rating10,
      RatingIdDummies.ratingId11 -> RatingDummies.rating11,
      RatingIdDummies.ratingId12 -> RatingDummies.rating12
    )
  )

  def get(id: RatingId): Option[Rating] = repository.get(id)
  def getAll: List[Rating] = repository.getAll
  def put(id: RatingId, value: Rating): String = repository.put(id, value)
  def delete(id: RatingId): String = repository.delete(id)
}
