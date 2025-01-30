package dummies.repositories

import dummies.ids.ChatIdDummies
import dummies.objects.ChatDummies
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId

object ChatRepository {
  private val repository: InMemoryRepository[ChatId, Chat] = InMemoryRepository(
    Map(
      ChatIdDummies.chatId1 -> ChatDummies.chat1,
      ChatIdDummies.chatId2 -> ChatDummies.chat2,
      ChatIdDummies.chatId3 -> ChatDummies.chat3,
      ChatIdDummies.chatId4 -> ChatDummies.chat4,
      ChatIdDummies.chatId5 -> ChatDummies.chat5,
      ChatIdDummies.chatId6 -> ChatDummies.chat6
    )
  )

  def get(id: ChatId): Option[Chat] = repository.get(id)
  def getAll: List[Chat] = repository.getAll
  def put(id: ChatId, value: Chat): String = repository.put(id, value)
  def delete(id: ChatId): String = repository.delete(id)
}
