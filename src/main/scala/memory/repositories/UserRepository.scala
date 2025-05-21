package memory.repositories

import memory.ids.UserIds
import memory.objects.Users
import modelClasses.app.user.User
import modelClasses.ids.User.UserId

object UserRepository {
  private val repository: InMemoryRepository[UserId, User] = InMemoryRepository(
    Map(
      UserIds.userId1 -> Users.user1,
      UserIds.userId2 -> Users.user2,
      UserIds.userId3 -> Users.user3,
      UserIds.userId4 -> Users.user4,
      UserIds.userId5 -> Users.user5
    )
  )

  def get(id: UserId): Option[User] = repository.get(id)
  def getAll: List[User] = repository.getAll
  def getMany(ids: List[UserId]) = repository.getMany(ids)
  def findByUsername(username: String): List[User] = repository.getAll.filter(_.profile.username.contains(username))
  def put(id: UserId, value: User): String = repository.put(id, value)
  def delete(id: UserId): String = repository.delete(id)
}
