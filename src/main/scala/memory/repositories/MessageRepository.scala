package memory.repositories

import memory.ids.MessageIds
import memory.objects.Messages
import domain.app.chatting.Message
import domain.ids.Chatting.MessageId

object MessageRepository {
  private val repository: InMemoryRepository[MessageId, Message] = InMemoryRepository(
    Map(
      MessageIds.messageId1 -> Messages.message1,
      MessageIds.messageId2 -> Messages.message2,
      MessageIds.messageId3 -> Messages.message3,
      MessageIds.messageId4 -> Messages.message4,
      MessageIds.messageId5 -> Messages.message5,
      MessageIds.messageId6 -> Messages.message6,
      MessageIds.messageId7 -> Messages.message7,
      MessageIds.messageId8 -> Messages.message8,
      MessageIds.messageId9 -> Messages.message9
    )
  )

  def get(id: MessageId): Option[Message] = repository.get(id)
  def getAll: List[Message] = repository.getAll
  def getMany(ids: List[MessageId]) = repository.getMany(ids)
  def put(id: MessageId, value: Message): String = repository.put(id, value)
  def delete(id: MessageId): String = repository.delete(id)
}
