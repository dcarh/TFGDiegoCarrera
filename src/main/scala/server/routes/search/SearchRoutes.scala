package server.routes.search

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.SearchEndpoints
import org.http4s.HttpRoutes
import server.logics.SearchLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object SearchRoutes {

  private val searchMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchMovie.serverLogic(SearchLogics.searchMovie))

  private val searchTVShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchTVShow.serverLogic(SearchLogics.searchTVShow))

  private val searchVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchVideogame.serverLogic(SearchLogics.searchVideogame))

//  private val searchBook: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]().
//      toRoutes(SearchEndpoints.searchBook.serverLogic(SearchLogics.searchBook))

  private val searchMediaContentList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchMediaContentList.serverLogic(SearchLogics.searchMediaContentList))

  private val searchUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchUser.serverLogic(SearchLogics.searchUser))

  val searchRoutes: HttpRoutes[IO] =
    searchMovie <+>
      searchTVShow <+>
      searchVideogame <+>
//      searchBook <+>
      searchMediaContentList <+>
      searchUser

}
