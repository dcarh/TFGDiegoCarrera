package dummies.repositories

import dummies.ids.EntryIdDummies
import dummies.objects.EntryDummies
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId

object EntryRepository {
  private val repository: InMemoryRepository[EntryId, Entry] = InMemoryRepository(
    Map(
      EntryIdDummies.entryId1 -> EntryDummies.entry1,
      EntryIdDummies.entryId2 -> EntryDummies.entry2,
      EntryIdDummies.entryId3 -> EntryDummies.entry3,
      EntryIdDummies.entryId4 -> EntryDummies.entry4,
      EntryIdDummies.entryId5 -> EntryDummies.entry5,
      EntryIdDummies.entryId6 -> EntryDummies.entry6,
      EntryIdDummies.entryId7 -> EntryDummies.entry7,
      EntryIdDummies.entryId8 -> EntryDummies.entry8,
      EntryIdDummies.entryId9 -> EntryDummies.entry9,
      EntryIdDummies.entryId10 -> EntryDummies.entry10,
      EntryIdDummies.entryId11 -> EntryDummies.entry11,
      EntryIdDummies.entryId12 -> EntryDummies.entry12,
      EntryIdDummies.entryId13 -> EntryDummies.entry13,
      EntryIdDummies.entryId14 -> EntryDummies.entry14,
      EntryIdDummies.entryId15 -> EntryDummies.entry15,
      EntryIdDummies.entryId16 -> EntryDummies.entry16,
      EntryIdDummies.entryId17 -> EntryDummies.entry17,
      EntryIdDummies.entryId18 -> EntryDummies.entry18,
    )
  )

  def get(id: EntryId): Option[Entry] = repository.get(id)
  def getAll: List[Entry] = repository.getAll
  def getMany(ids: List[EntryId]) = repository.getMany(ids)
  def put(id: EntryId, value: Entry): String = repository.put(id, value)
  def delete(id: EntryId): String = repository.delete(id)
}
