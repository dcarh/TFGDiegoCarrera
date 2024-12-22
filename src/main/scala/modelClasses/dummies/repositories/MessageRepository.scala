package modelClasses.dummies.repositories

import modelClasses.app.chatting.Message
import modelClasses.ids.Chatting.MessageId

import modelClasses.dummies.ids.MessageIdDummies
import modelClasses.dummies.objects.MessageDummies

object MessageRepository {
  private val repository: InMemoryRepository[MessageId, Message] = InMemoryRepository(
    Map(
      MessageIdDummies.messageId1 -> MessageDummies.message1,
      MessageIdDummies.messageId2 -> MessageDummies.message2,
      MessageIdDummies.messageId3 -> MessageDummies.message3,
      MessageIdDummies.messageId4 -> MessageDummies.message4,
      MessageIdDummies.messageId5 -> MessageDummies.message5,
      MessageIdDummies.messageId6 -> MessageDummies.message6,
      MessageIdDummies.messageId7 -> MessageDummies.message7,
      MessageIdDummies.messageId8 -> MessageDummies.message8,
      MessageIdDummies.messageId9 -> MessageDummies.message9
    )
  )

  def get(id: MessageId): Option[Message] = repository.get(id)
  def getAll: List[Message] = repository.getAll
  def put(id: MessageId, value: Message): String = repository.put(id, value)
  def delete(id: MessageId): String = repository.delete(id)
}
