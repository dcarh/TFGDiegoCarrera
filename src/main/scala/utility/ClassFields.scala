package utility

import scala.deriving.Mirror
import scala.compiletime.{constValueTuple, summonInline}

object ClassFields {

  inline def getField[T <: Product](instance: T, fieldName: String)(using mirror: Mirror.ProductOf[T]): Option[Any] = {
    val labels = constValueTuple[mirror.MirroredElemLabels].productIterator.map(_.toString).toList
    val index = labels.indexOf(fieldName)
    if (index >= 0) Some(instance.productElement(index)) else None
  }

  inline def getFieldAsList[T <: Product, A](instance: T, fieldName: String)(using mirror: Mirror.ProductOf[T]): Option[List[A]] =
    getField(instance, fieldName).map(_.asInstanceOf[List[A]])

  inline def updateField[T <: Product](instance: T, fieldName: String, newValue: Any)(using mirror: Mirror.ProductOf[T]): Option[T] = {
    val labels = constValueTuple[mirror.MirroredElemLabels].productIterator.map(_.toString).toList
    val index = labels.indexOf(fieldName)

    if (index >= 0) {
      val values = instance.productIterator.toList.updated(index, newValue)
      Some(mirror.fromProduct(Tuple.fromArray(values.toArray).asInstanceOf[mirror.MirroredElemTypes]))
    } else {
      None
    }
  }

}
