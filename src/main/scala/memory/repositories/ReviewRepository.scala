package memory.repositories

import memory.ids.ReviewIds
import memory.objects.Reviews
import modelClasses.app.social.Review
import modelClasses.ids.Social.ReviewId

object ReviewRepository {
  private val repository: InMemoryRepository[ReviewId, Review] = InMemoryRepository(
    Map(
      ReviewIds.reviewId1 -> Reviews.review1,
      ReviewIds.reviewId2 -> Reviews.review2,
      ReviewIds.reviewId3 -> Reviews.review3,
      ReviewIds.reviewId4 -> Reviews.review4,
      ReviewIds.reviewId5 -> Reviews.review5,
      ReviewIds.reviewId6 -> Reviews.review6
    )
  )

  def get(id: ReviewId): Option[Review] = repository.get(id)
  def getAll: List[Review] = repository.getAll
  def getMany(ids: List[ReviewId]) = repository.getMany(ids)
  def put(id: ReviewId, value: Review): String = repository.put(id, value)
  def delete(id: ReviewId): String = repository.delete(id)
}
