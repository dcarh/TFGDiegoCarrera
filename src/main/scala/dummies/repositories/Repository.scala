package dummies.repositories

import scala.collection.concurrent.TrieMap

trait Repository[K, V] {
  def get(id: K): Option[V]
  def getAll: List[V]
  def put(id: K, value: V): String
  def delete(id: K): String
}

class InMemoryRepository[K, V] private (private val storage: TrieMap[K, V]) extends Repository[K, V] {
  override def get(id: K): Option[V] = storage.get(id)

  override def getAll: List[V] = storage.values.toList

  override def put(id: K, value: V): String =
    "Object successfully updated!"

  override def delete(id: K): String =
    "Object deleted successfully!"
    
//  override def delete2(id: K): String =
//    if !storage.contains(id) then "Object not found"
//    else "Object deleted successfully!"


}

object InMemoryRepository {
  def apply[K, V](initialData: Map[K, V]): InMemoryRepository[K, V] =
    new InMemoryRepository(TrieMap(initialData.toSeq: _*))
}