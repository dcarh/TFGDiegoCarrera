package modelClasses

import sttp.tapir.generic.auto._

abstract class Element {
  type Id <: AnyVal
}

//case class Element(
//                  id: Element.Id,
//                  elementType: String
//                  )

// TODO: Que Element sea un sealed trait extendido por Movie, TVShow, Season, Episode, Videogame y Book

object Element {
  type Id = Long
}