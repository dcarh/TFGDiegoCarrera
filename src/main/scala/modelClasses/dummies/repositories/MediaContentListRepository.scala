package modelClasses.dummies.repositories

import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId

import modelClasses.dummies.ids.MediaContentListIdDummies
import modelClasses.dummies.objects.MediaContentListDummies

object MediaContentListRepository {
  private val repository: InMemoryRepository[MediaContentListId, MediaContentList] = InMemoryRepository(
    Map(
      MediaContentListIdDummies.mediaContentListId1 -> MediaContentListDummies.mediaContentList1,
      MediaContentListIdDummies.mediaContentListId2 -> MediaContentListDummies.mediaContentList2,
      MediaContentListIdDummies.mediaContentListId3 -> MediaContentListDummies.mediaContentList3,
      MediaContentListIdDummies.mediaContentListId4 -> MediaContentListDummies.mediaContentList4
    )
  )

  def get(id: MediaContentListId): Option[MediaContentList] = repository.get(id)
  def getAll: List[MediaContentList] = repository.getAll
  def put(id: MediaContentListId, value: MediaContentList): String = repository.put(id, value)
  def delete(id: MediaContentListId): String = repository.delete(id)
}
