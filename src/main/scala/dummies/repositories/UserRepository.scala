package dummies.repositories

import dummies.ids.UserIdDummies
import dummies.objects.UserDummies
import modelClasses.app.user.User
import modelClasses.ids.User.UserId

object UserRepository {
  private val repository: InMemoryRepository[UserId, User] = InMemoryRepository(
    Map(
      UserIdDummies.userId1 -> UserDummies.user1,
      UserIdDummies.userId2 -> UserDummies.user2,
      UserIdDummies.userId3 -> UserDummies.user3,
      UserIdDummies.userId4 -> UserDummies.user4,
      UserIdDummies.userId5 -> UserDummies.user5
    )
  )

  def get(id: UserId): Option[User] = repository.get(id)
  def getAll: List[User] = repository.getAll
  def getMany(ids: List[UserId]) = repository.getMany(ids)
  def findByUsername(username: String): List[User] = repository.getAll.filter(_.profile.username.contains(username))
  def put(id: UserId, value: User): String = repository.put(id, value)
  def delete(id: UserId): String = repository.delete(id)
}
