package memory.repositories

import memory.ids.LikeIds
import memory.objects.Likes
import domain.app.social.Like
import domain.ids.Social.LikeId

object LikeRepository {
  private val repository: InMemoryRepository[LikeId, Like] = InMemoryRepository(
    Map(
      LikeIds.likeId1 -> Likes.like1,
      LikeIds.likeId2 -> Likes.like2,
      LikeIds.likeId3 -> Likes.like3,
      LikeIds.likeId4 -> Likes.like4
    )
  )

  def get(id: LikeId): Option[Like] = repository.get(id)
  def getAll: List[Like] = repository.getAll
  def getMany(ids: List[LikeId]) = repository.getMany(ids)
  def put(id: LikeId, value: Like): String = repository.put(id, value)
  def delete(id: LikeId): String = repository.delete(id)
}
