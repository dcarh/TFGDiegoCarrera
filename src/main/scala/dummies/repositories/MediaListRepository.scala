package dummies.repositories

import dummies.ids.MediaListIdDummies
import dummies.objects.MediaListDummies
import modelClasses.app.social.MediaList
import modelClasses.ids.Social.MediaListId

object MediaListRepository {
  private val repository: InMemoryRepository[MediaListId, MediaList] = InMemoryRepository(
    Map(
      MediaListIdDummies.mediaListId1 -> MediaListDummies.mediaContentList1,
      MediaListIdDummies.mediaListId2 -> MediaListDummies.mediaContentList2,
      MediaListIdDummies.mediaListId3 -> MediaListDummies.mediaContentList3,
      MediaListIdDummies.mediaListId4 -> MediaListDummies.mediaContentList4,
      MediaListIdDummies.mediaListId5 -> MediaListDummies.mediaContentList5
    )
  )

  def get(id: MediaListId): Option[MediaList] = repository.get(id)
  def getAll: List[MediaList] = repository.getAll
  def findByTitle(title: String): List[MediaList] = repository.getAll.filter(_.title.contains(title))
  def put(id: MediaListId, value: MediaList): String = repository.put(id, value)
  def delete(id: MediaListId): String = repository.delete(id)
}
