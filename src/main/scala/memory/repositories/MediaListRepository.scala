package memory.repositories

import memory.ids.MediaListIds
import memory.objects.MediaLists
import modelClasses.app.social.MediaList
import modelClasses.ids.Social.MediaListId

object MediaListRepository {
  private val repository: InMemoryRepository[MediaListId, MediaList] = InMemoryRepository(
    Map(
      MediaListIds.mediaListId1 -> MediaLists.mediaList1,
      MediaListIds.mediaListId2 -> MediaLists.mediaList2,
      MediaListIds.mediaListId3 -> MediaLists.mediaList3,
      MediaListIds.mediaListId4 -> MediaLists.mediaList4,
      MediaListIds.mediaListId5 -> MediaLists.mediaList5
    )
  )

  def get(id: MediaListId): Option[MediaList] = repository.get(id)
  def getAll: List[MediaList] = repository.getAll
  def getMany(ids: List[MediaListId]) = repository.getMany(ids)
  def findByTitle(title: String): List[MediaList] = repository.getAll.filter(_.title.contains(title))
  def put(id: MediaListId, value: MediaList): String = repository.put(id, value)
  def delete(id: MediaListId): String = repository.delete(id)
}
