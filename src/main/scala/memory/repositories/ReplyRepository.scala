package memory.repositories

import memory.ids.ReplyIds
import memory.objects.Replies
import modelClasses.app.social.Reply
import modelClasses.ids.Social.ReplyId

object ReplyRepository {
  private val repository: InMemoryRepository[ReplyId, Reply] = InMemoryRepository(
    Map(
      ReplyIds.replyId1 -> Replies.reply1,
      ReplyIds.replyId2 -> Replies.reply2,
      ReplyIds.replyId3 -> Replies.reply3,
      ReplyIds.replyId4 -> Replies.reply4
    )
  )

  def get(id: ReplyId): Option[Reply] = repository.get(id)
  def getAll: List[Reply] = repository.getAll
  def getMany(ids: List[ReplyId]) = repository.getMany(ids)
  def put(id: ReplyId, value: Reply): String = repository.put(id, value)
  def delete(id: ReplyId): String = repository.delete(id)
}
