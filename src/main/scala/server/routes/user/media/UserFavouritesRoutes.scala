package server.routes.user.media

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserFavouritesEndpoints.*
import server.logics.user.media.UserFavouritesLogics.*

object UserFavouritesRoutes {

  val getFavouritesRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getFavourites.serverLogic(getFavouritesLogic))

  val addFavouriteMovieRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addFavouriteMovie.serverLogic(addFavouriteMovieLogic))

  val addFavouriteTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addFavouriteTvShow.serverLogic(addFavouriteTvShowLogic))
  
  val addFavouriteVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addFavouriteVideogame.serverLogic(addFavouriteVideogameLogic))
  
  val addFavouriteBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addFavouriteBook.serverLogic(addFavouriteBookLogic))

  val deleteFavouriteMovieRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteFavouriteMovie.serverLogic(deleteFavouriteMovieLogic))

  val deleteFavouriteTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteFavouriteTvShow.serverLogic(deleteFavouriteTvShowLogic))

  val deleteFavouriteVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteFavouriteVideogame.serverLogic(deleteFavouriteVideogameLogic))

  val deleteFavouriteBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteFavouriteBook.serverLogic(deleteFavouriteBookLogic))
}
