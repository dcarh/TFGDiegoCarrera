package dummies.repositories

import dummies.ids.MediaListIdDummies
import dummies.objects.MediaListDummies
import modelClasses.app.social.MediaList
import modelClasses.ids.Social.MediaListId

object MediaListRepository {
  private val repository: InMemoryRepository[MediaListId, MediaList] = InMemoryRepository(
    Map(
      MediaListIdDummies.mediaListId1 -> MediaListDummies.mediaList1,
      MediaListIdDummies.mediaListId2 -> MediaListDummies.mediaList2,
      MediaListIdDummies.mediaListId3 -> MediaListDummies.mediaList3,
      MediaListIdDummies.mediaListId4 -> MediaListDummies.mediaList4,
      MediaListIdDummies.mediaListId5 -> MediaListDummies.mediaList5
    )
  )

  def get(id: MediaListId): Option[MediaList] = repository.get(id)
  def getAll: List[MediaList] = repository.getAll
  def getMany(ids: List[MediaListId]) = repository.getMany(ids)
  def findByTitle(title: String): List[MediaList] = repository.getAll.filter(_.title.contains(title))
  def put(id: MediaListId, value: MediaList): String = repository.put(id, value)
  def delete(id: MediaListId): String = repository.delete(id)
}
