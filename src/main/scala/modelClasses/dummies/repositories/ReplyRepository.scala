package modelClasses.dummies.repositories

import modelClasses.app.social.Reply
import modelClasses.ids.Social.ReplyId

import modelClasses.dummies.ids.ReplyIdDummies
import modelClasses.dummies.objects.ReplyDummies

object ReplyRepository {
  private val repository: InMemoryRepository[ReplyId, Reply] = InMemoryRepository(
    Map(
      ReplyIdDummies.replyId1 -> ReplyDummies.reply1,
      ReplyIdDummies.replyId2 -> ReplyDummies.reply2,
      ReplyIdDummies.replyId3 -> ReplyDummies.reply3,
      ReplyIdDummies.replyId4 -> ReplyDummies.reply4
    )
  )

  def get(id: ReplyId): Option[Reply] = repository.get(id)
  def getAll: List[Reply] = repository.getAll
  def put(id: ReplyId, value: Reply): String = repository.put(id, value)
  def delete(id: ReplyId): String = repository.delete(id)
}
