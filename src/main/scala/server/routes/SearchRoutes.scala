package server.routes

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.SearchEndpoints.*
import server.logics.SearchLogics.*

object SearchRoutes {

  val searchMovieRoute: HttpRoutes[IO] = 
    Http4sServerInterpreter[IO]().toRoutes(searchMovieEndpoint.serverLogic(searchMovieLogic))

  val searchTVShowRoute: HttpRoutes[IO] = 
    Http4sServerInterpreter[IO]().toRoutes(searchTVShowEndpoint.serverLogic(searchTVShowLogic))

  val searchVideogameRoute: HttpRoutes[IO] = 
    Http4sServerInterpreter[IO]().toRoutes(searchVideogameEndpoint.serverLogic(searchVideogameLogic))

  val searchBookRoute: HttpRoutes[IO] = 
    Http4sServerInterpreter[IO]().toRoutes(searchBookEndpoint.serverLogic(searchBookLogic))

  val searchMediaContentListRoute: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(searchMediaContentListEndpoint.serverLogic(searchMediaContentListLogic))

  val searchUserRoute: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(searchUserEndpoint.serverLogic(searchUserLogic))

}
