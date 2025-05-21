package memory.repositories

import memory.ids.EntryIds
import memory.objects.Entries
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId

object EntryRepository {
  private val repository: InMemoryRepository[EntryId, Entry] = InMemoryRepository(
    Map(
      EntryIds.entryId1 -> Entries.entry1,
      EntryIds.entryId2 -> Entries.entry2,
      EntryIds.entryId3 -> Entries.entry3,
      EntryIds.entryId4 -> Entries.entry4,
      EntryIds.entryId5 -> Entries.entry5,
      EntryIds.entryId6 -> Entries.entry6,
      EntryIds.entryId7 -> Entries.entry7,
      EntryIds.entryId8 -> Entries.entry8,
      EntryIds.entryId9 -> Entries.entry9,
      EntryIds.entryId10 -> Entries.entry10,
      EntryIds.entryId11 -> Entries.entry11,
      EntryIds.entryId12 -> Entries.entry12,
      EntryIds.entryId13 -> Entries.entry13,
      EntryIds.entryId14 -> Entries.entry14,
      EntryIds.entryId15 -> Entries.entry15,
      EntryIds.entryId16 -> Entries.entry16,
      EntryIds.entryId17 -> Entries.entry17,
      EntryIds.entryId18 -> Entries.entry18,
    )
  )

  def get(id: EntryId): Option[Entry] = repository.get(id)
  def getAll: List[Entry] = repository.getAll
  def getMany(ids: List[EntryId]) = repository.getMany(ids)
  def put(id: EntryId, value: Entry): String = repository.put(id, value)
  def delete(id: EntryId): String = repository.delete(id)
}
