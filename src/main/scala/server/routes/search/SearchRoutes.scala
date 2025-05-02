package server.routes.search

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.search.SearchEndpoints
import org.http4s.HttpRoutes
import server.logics.search.SearchLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object SearchRoutes {

  private val searchMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchMovie.serverLogic(SearchLogics.searchMovie))

  private val searchTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchTvShow.serverLogic(SearchLogics.searchTvShow))

  private val searchVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchVideogame.serverLogic(SearchLogics.searchVideogame))

  private val searchBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchBook.serverLogic(SearchLogics.searchBook))

  private val searchMediaList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchMediaList.serverLogic(SearchLogics.searchMediaList))

  private val searchUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(SearchEndpoints.searchUser.serverLogic(SearchLogics.searchUser))

  val searchRoutes: HttpRoutes[IO] =
    searchMovie       <+>
      searchTvShow    <+>
      searchVideogame <+>
      searchBook      <+>
      searchMediaList <+>
      searchUser

}
