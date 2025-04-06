package server.routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.BookEndpoints
import org.http4s.HttpRoutes
import server.logics.media.BookLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object BookRoutes {
  
  private val getBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(BookEndpoints.getBook.serverLogic(BookLogics.getBook))
    
  private val getEntriesForBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(BookEndpoints.getEntriesForBook.serverLogic(BookLogics.getEntriesForBook))
    
  private val getMediaListsForBooks: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(BookEndpoints.getMediaListsForBook.serverLogic(BookLogics.getMediaListsForBook))

  val bookRoutes: HttpRoutes[IO] =
    getBook <+>
      getEntriesForBook <+>
      getMediaListsForBooks

}
