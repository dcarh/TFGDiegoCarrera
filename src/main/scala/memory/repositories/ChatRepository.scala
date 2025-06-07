package memory.repositories

import memory.ids.ChatIds
import memory.objects.Chats
import domain.app.chatting.Chat
import domain.ids.Chatting.ChatId

object ChatRepository {
  private val repository: InMemoryRepository[ChatId, Chat] = InMemoryRepository(
    Map(
      ChatIds.chatId1 -> Chats.chat1,
      ChatIds.chatId2 -> Chats.chat2,
      ChatIds.chatId3 -> Chats.chat3,
      ChatIds.chatId4 -> Chats.chat4,
      ChatIds.chatId5 -> Chats.chat5,
      ChatIds.chatId6 -> Chats.chat6
    )
  )

  def get(id: ChatId): Option[Chat] = repository.get(id)
  def getAll: List[Chat] = repository.getAll
  def getMany(ids: List[ChatId]) = repository.getMany(ids)
  def put(id: ChatId, value: Chat): String = repository.put(id, value)
  def delete(id: ChatId): String = repository.delete(id)
}
