package modelClasses.dummies.repositories

import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId

import modelClasses.dummies.ids.ChatIdDummies
import modelClasses.dummies.objects.ChatDummies

object ChatRepository {
  private val repository: InMemoryRepository[ChatId, Chat] = InMemoryRepository(
    Map(
      ChatIdDummies.chatId1 -> ChatDummies.chat1,
      ChatIdDummies.chatId2 -> ChatDummies.chat2,
      ChatIdDummies.chatId3 -> ChatDummies.chat3
    )
  )

  def get(id: ChatId): Option[Chat] = repository.get(id)
  def getAll: List[Chat] = repository.getAll
}
