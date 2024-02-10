package api

/*
import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto.*
import io.circe.syntax.*
import model.{User, Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Review, Reply}
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.model.UsernamePassword
import java.util.UUID

class Prueba {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  val user1: User = User(727891, "d.carrerah.2019@alumnos.urjc.es", UsernamePassword("dcarrerah", Some("6756897845563")))

  // Define un método para crear endpoints para las listas de elementos
  private def userListEndpoint(path: String): PublicEndpoint[String, Unit, List[Element], Any] =
    endpoint.in(pathUsername).in(path).out(jsonBody[List[Element]])

  // Define un método para crear endpoints para las listas de elementos de un tipo específico
  private def typedUserListEndpoint[T](path: String): PublicEndpoint[String, Unit, List[T], Any] =
    endpoint.in(pathUsername).in(path).out(jsonBody[List[T]])

  // Define una lista de tipos que representan los tipos de elementos que se manejan
  val elementTypes = List("movies", "tv_shows", "seasons", "episodes", "videogames", "books")

  // Define una lista de acciones que se realizarán para cada tipo de elemento
  val listActions = List("completed", "in_progress", "pending", "on_hold", "abandoned", "wishlist", "owned", "likes")

  // Crea los endpoints para cada combinación de tipo de elemento y acción
  val elementEndpoints: List[PublicEndpoint[String, Unit, _, Any]] =
    for {
      elementType <- elementTypes
      listAction <- listActions
    } yield typedUserListEndpoint(s"$listAction/$elementType")

  // Descomprime la lista de endpoints en variables con nombres descriptivos
  val (completedMoviesEndpoint :: inProgressMoviesEndpoint :: pendingMoviesEndpoint ::
    onHoldMoviesEndpoint :: abandonedMoviesEndpoint :: wishlistMoviesEndpoint ::
    ownedMoviesEndpoint :: likesMoviesEndpoint ::
    completedTVShowsEndpoint :: inProgressTVShowsEndpoint :: pendingTVShowsEndpoint ::
    onHoldTVShowsEndpoint :: abandonedTVShowsEndpoint :: wishlistTVShowsEndpoint ::
    ownedTVShowsEndpoint :: likesTVShowsEndpoint ::
    completedSeasonsEndpoint :: inProgressSeasonsEndpoint :: pendingSeasonsEndpoint ::
    onHoldSeasonsEndpoint :: abandonedSeasonsEndpoint :: wishlistSeasonsEndpoint ::
    ownedSeasonsEndpoint :: likesSeasonsEndpoint ::
    completedEpisodesEndpoint :: inProgressEpisodesEndpoint :: pendingEpisodesEndpoint ::
    onHoldEpisodesEndpoint :: abandonedEpisodesEndpoint :: wishlistEpisodesEndpoint ::
    ownedEpisodesEndpoint :: likesEpisodesEndpoint ::
    completedVideogamesEndpoint :: inProgressVideogamesEndpoint :: pendingVideogamesEndpoint ::
    onHoldVideogamesEndpoint :: abandonedVideogamesEndpoint :: wishlistVideogamesEndpoint ::
    ownedVideogamesEndpoint :: likesVideogamesEndpoint ::
    completedBooksEndpoint :: inProgressBooksEndpoint :: pendingBooksEndpoint ::
    onHoldBooksEndpoint :: abandonedBooksEndpoint :: wishlistBooksEndpoint ::
    ownedBooksEndpoint :: likesBooksEndpoint :: likesReviewsEndpoint ::
    likesRepliesEndpoint :: likesListsEndpoint :: _) = elementEndpoints

}

*/