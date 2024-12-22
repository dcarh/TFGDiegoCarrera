package modelClasses.dummies.repositories

import modelClasses.app.social.Like
import modelClasses.ids.Social.LikeId

import modelClasses.dummies.ids.LikeIdDummies
import modelClasses.dummies.objects.LikeDummies

object LikeRepository {
  private val repository: InMemoryRepository[LikeId, Like] = InMemoryRepository(
    Map(
      LikeIdDummies.likeId1 -> LikeDummies.like1,
      LikeIdDummies.likeId2 -> LikeDummies.like2,
      LikeIdDummies.likeId3 -> LikeDummies.like3,
      LikeIdDummies.likeId4 -> LikeDummies.like4
    )
  )

  def get(id: LikeId): Option[Like] = repository.get(id)
  def getAll: List[Like] = repository.getAll
  def put(id: LikeId, value: Like): String = repository.put(id, value)
  def delete(id: LikeId): String = repository.delete(id)
}
