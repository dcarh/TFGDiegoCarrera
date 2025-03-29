package server.logics.search

import cats.effect.IO
import cats.implicits.*
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import dummies.repositories.{MediaListRepository, UserRepository}
import endpoints.googleBooks.Books
import endpoints.igdb.Videogames
import endpoints.tmdb.{Movies, TvShows}
import modelClasses.app.media.Videogame
import modelClasses.app.social.MediaList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.googleBooks.BooksRequests.{ListOfSearchedBooks, SearchedBook}
import modelClasses.igdb.VideogameRequests.VideogameAllFields
import modelClasses.tmdb.Common.{Result, Results}

object SearchLogics {

  val searchMovie: ((String, Option[String])) => IO[Either[UserError, List[Result]]] =
    (title, sortByOption) =>
      TMDBClient.executeRequest(Movies.searchMoviesEndpoint, title).flatMap {
        case Right(results: Results) =>
          val listOfMovies = results.results

          // TODO: De momento esto no da errores pero no funciona para las fechas, ya que son Strings
          val sortedMovies: Either[UserError, List[Result]] = sortByOption match {
            case Some("least_tmdb_popular") => Right(listOfMovies.sortBy(_.popularity))
            case Some("most_tmdb_popular") => Right(listOfMovies.sortBy(_.popularity).reverse)
            case Some("oldest") => Right(listOfMovies.sortBy(_.release_date))
            case Some("newest") => Right(listOfMovies.sortBy(_.release_date).reverse)
            case Some("worst_tmdb_voted") => Right(listOfMovies.sortBy(_.vote_average))
            case Some("best_tmdb_voted") => Right(listOfMovies.sortBy(_.vote_average).reverse)
            case None => Right(listOfMovies)
            case _ => Left(BadRequest("Parameter not supported"))
          }

          IO(sortedMovies)

        case Left(error) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }


  val searchTvShow: ((String, Option[String])) => IO[Either[UserError, List[Result]]] =
    (title, sortByOption) =>
      TMDBClient.executeRequest(TvShows.searchTvShowsEndpoint, title).flatMap {
        case Right(results: Results) =>
          val listOfTvShows = results.results
          
          // TODO: De momento esto no da errores pero no funciona para las fechas, ya que son Strings
          val sortedTvShows: Either[UserError, List[Result]] = sortByOption match {
            case Some("least_tmdb_popular") => Right(listOfTvShows.sortBy(_.popularity))
            case Some("most_tmdb_popular") => Right(listOfTvShows.sortBy(_.popularity).reverse)
            case Some("oldest") => Right(listOfTvShows.sortBy(_.first_air_date))
            case Some("newest") => Right(listOfTvShows.sortBy(_.first_air_date).reverse)
            case Some("worst_tmdb_voted") => Right(listOfTvShows.sortBy(_.vote_average))
            case Some("best_tmdb_voted") => Right(listOfTvShows.sortBy(_.vote_average).reverse)
            case None => Right(listOfTvShows)
            case _ => Left(BadRequest("Parameter not supported"))
          }

          IO(sortedTvShows)

        case Left(error) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  // TODO: Seguir por aquí, comprobar si va bien o no
  val searchVideogame: ((String, Option[String])) => IO[Either[UserError, List[Videogame]]] =
    (title, sortByOption) =>
      IGDBClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, title).flatMap {
        case Right(requestedListOfVideogames: List[VideogameAllFields]) =>
          val listOfVideogames = requestedListOfVideogames.map(
            requestedVideogame =>
              Videogame(
                requestedVideogame = requestedVideogame
              )
              // TODO: Podría devolver directamente el objeto VideogameAllFields y ya está
          )

          val sortedVideogames = sortByOption match {
            case Some("oldest") => Right(listOfVideogames.sortBy(_.requestedVideogame.first_release_date))
            case Some("newest") => Right(listOfVideogames.sortBy(_.requestedVideogame.first_release_date).reverse)
            case Some("worst_igdb_rated") => Right(listOfVideogames.sortBy(_.requestedVideogame.total_rating))
            case Some("best_igdb_rated") => Right(listOfVideogames.sortBy(_.requestedVideogame.total_rating).reverse)
            case None => Right(listOfVideogames)
            case _ => Left(BadRequest("Parameter not supported"))
          }
          IO(sortedVideogames)

        case Left(error: UserError) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val searchBook: ((String, Option[String])) => IO[Either[UserError, List[SearchedBook]]] =
    (title, sortByOption) =>
      GoogleBooksClient.executeRequest(Books.searchBooksEndpoint, (title, "lite")).flatMap {
        case Right(listOfSearchedBooks: ListOfSearchedBooks) =>
          val listOfBooks = listOfSearchedBooks.items

          // TODO: De momento esto no da errores pero no funciona, ya que las fechas son Strings
          val sortedBooks = sortByOption match {
            case Some("oldest") => Right(listOfBooks.sortBy(_.volumeInfo.publishedDate))
            case Some("newest") => Right(listOfBooks.sortBy(_.volumeInfo.publishedDate).reverse)
            case None => Right(listOfBooks)
            case _ => Left(BadRequest("Parameter not supported"))
          }
          IO(sortedBooks)

        case Left(error: UserError) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

//  val searchBook2: ((String, Option[String])) => IO[Either[UserError, List[SearchedBook]]] =
//    (title, sortByOption) =>
//      GoogleBooksClient.executeRequest(Books.searchBooksEndpoint2, (title, "relevance", "lite", "en")).flatMap {
//        case Right(listOfSearchedBooks: ListOfSearchedBooks) =>
//          val listOfBooks = listOfSearchedBooks.items
//
//          val sortedBooks = sortByOption match {
//            case Some("oldest") => Right(listOfBooks.sortBy(_.volumeInfo.publishedDate))
//            case Some("newest") => Right(listOfBooks.sortBy(_.volumeInfo.publishedDate).reverse)
//            case None => Right(listOfBooks)
//            case _ => Left(BadRequest("Parameter not supported"))
//          }
//          IO(sortedBooks)
//
//        case Left(error: UserError) => IO(Left(error))
//      }.handleError {
//        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
//      }

  val searchMediaList: String => IO[Either[UserError, List[MediaList]]] = {
    title =>
      IO(Right(MediaListRepository.findByTitle(title)))
        .handleError {
          case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
        }
  }

  val searchUser: String => IO[Either[UserError, List[User]]] = {
    username =>
      IO(Right(UserRepository.findByUsername(username)))
        .handleError {
          case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
        }
  }

}

