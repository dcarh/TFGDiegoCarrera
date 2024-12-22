package modelClasses.dummies.repositories

import modelClasses.app.social.Review
import modelClasses.ids.Social.ReviewId

import modelClasses.dummies.ids.ReviewIdDummies
import modelClasses.dummies.objects.ReviewDummies

object ReviewRepository {
  private val repository: InMemoryRepository[ReviewId, Review] = InMemoryRepository(
    Map(
      ReviewIdDummies.reviewId1 -> ReviewDummies.review1,
      ReviewIdDummies.reviewId2 -> ReviewDummies.review2,
      ReviewIdDummies.reviewId3 -> ReviewDummies.review3,
      ReviewIdDummies.reviewId4 -> ReviewDummies.review4,
      ReviewIdDummies.reviewId5 -> ReviewDummies.review5,
      ReviewIdDummies.reviewId6 -> ReviewDummies.review6
    )
  )

  def get(id: ReviewId): Option[Review] = repository.get(id)
  def getAll: List[Review] = repository.getAll
  def put(id: ReviewId, value: Review): String = repository.put(id, value)
  def delete(id: ReviewId): String = repository.delete(id)
}
