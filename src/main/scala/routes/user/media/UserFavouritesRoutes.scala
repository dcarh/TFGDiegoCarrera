package routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserFavouritesEndpoints
import logics.user.media.UserFavouritesLogics

object UserFavouritesRoutes {

  private val getFavourites: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.getFavourites.serverLogic(UserFavouritesLogics.getFavourites))

  private val addFavouriteMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.addFavouriteMovie.serverLogic(UserFavouritesLogics.addFavouriteMovie))

  private val addFavouriteTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.addFavouriteTvShow.serverLogic(UserFavouritesLogics.addFavouriteTvShow))
  
  private val addFavouriteVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.addFavouriteVideogame.serverLogic(UserFavouritesLogics.addFavouriteVideogame))
  
  private val addFavouriteBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.addFavouriteBook.serverLogic(UserFavouritesLogics.addFavouriteBook))

  private val deleteFavouriteMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.deleteFavouriteMovie.serverLogic(UserFavouritesLogics.deleteFavouriteMovie))

  private val deleteFavouriteTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.deleteFavouriteTvShow.serverLogic(UserFavouritesLogics.deleteFavouriteTvShow))

  private val deleteFavouriteVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.deleteFavouriteVideogame.serverLogic(UserFavouritesLogics.deleteFavouriteVideogame))

  private val deleteFavouriteBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserFavouritesEndpoints.deleteFavouriteBook.serverLogic(UserFavouritesLogics.deleteFavouriteBook))
    
  val userFavouritesRoutes: HttpRoutes[IO] =
    getFavourites              <+>
      addFavouriteMovie        <+>
      addFavouriteTvShow       <+>
      addFavouriteVideogame    <+>
      addFavouriteBook         <+>
      deleteFavouriteMovie     <+>
      deleteFavouriteTvShow    <+>
      deleteFavouriteVideogame <+>
      deleteFavouriteBook
}
