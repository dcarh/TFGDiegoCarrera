package server.logics.search

import cats.effect.IO
import cats.implicits.*
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import dummies.repositories.{MediaListRepository, UserRepository}
import endpoints.googleBooks.Books
import endpoints.igdb.Videogames
import endpoints.tmdb.{Movies, TvShows}
import modelClasses.app.social.MediaList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.googleBooks.BooksRequests.{ListOfSearchedBooks, SearchedBook}
import modelClasses.igdb.VideogameRequests.VideogameAllFields
import modelClasses.tmdb.Common.{Result, Results}
import utility.DateParser.*

object SearchLogics {

  val searchMovie: ((String, Option[String])) => IO[Either[UserError, List[Result]]] =
    (title, sortByOption) =>
      TMDBClient.executeRequest(Movies.searchMoviesEndpoint, title).flatMap {
        case Right(results: Results) =>
          val listOfMovies = results.results

          val sortedMovies: Either[UserError, List[Result]] = sortByOption match {
            case Some("least_tmdb_popular") => Right(listOfMovies.sortBy(_.popularity))
            case Some("most_tmdb_popular") => Right(listOfMovies.sortBy(_.popularity).reverse)
            case Some("oldest") => Right(listOfMovies.sortBy(r => parseDate(r.release_date).map(_.toEpochDay).getOrElse(Long.MaxValue)))
            case Some("newest") => Right(listOfMovies.sortBy(r => parseDate(r.release_date).map(_.toEpochDay).getOrElse(Long.MinValue)).reverse)
            case Some("worst_tmdb_voted") => Right(listOfMovies.sortBy(_.vote_average))
            case Some("best_tmdb_voted") => Right(listOfMovies.sortBy(_.vote_average).reverse)
            case None => Right(listOfMovies)
            case _ => Left(BadRequest("Parameter not supported"))
          }

          IO.pure(sortedMovies)

        case Left(error) => IO.pure(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }


  val searchTvShow: ((String, Option[String])) => IO[Either[UserError, List[Result]]] =
    (title, sortByOption) =>
      TMDBClient.executeRequest(TvShows.searchTvShowsEndpoint, title).flatMap {
        case Right(results: Results) =>
          val listOfTvShows = results.results
          
          val sortedTvShows: Either[UserError, List[Result]] = sortByOption match {
            case Some("least_tmdb_popular") => Right(listOfTvShows.sortBy(_.popularity))
            case Some("most_tmdb_popular") => Right(listOfTvShows.sortBy(_.popularity).reverse)
            case Some("oldest") => Right(listOfTvShows.sortBy(r => parseDate(r.first_air_date).map(_.toEpochDay).getOrElse(Long.MaxValue)))
            case Some("newest") => Right(listOfTvShows.sortBy(r => parseDate(r.first_air_date).map(_.toEpochDay).getOrElse(Long.MinValue)).reverse)
            case Some("worst_tmdb_voted") => Right(listOfTvShows.sortBy(_.vote_average))
            case Some("best_tmdb_voted") => Right(listOfTvShows.sortBy(_.vote_average).reverse)
            case None => Right(listOfTvShows)
            case _ => Left(BadRequest("Parameter not supported"))
          }

          IO.pure(sortedTvShows)

        case Left(error) => IO.pure(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val searchVideogame: ((String, Option[String])) => IO[Either[UserError, List[VideogameAllFields]]] =
    (title, sortByOption) =>
      IGDBClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, title).flatMap {
        case Right(listOfRequestedVideogames: List[VideogameAllFields]) =>

          val sortedVideogames = sortByOption match {
            case Some("oldest") => Right(listOfRequestedVideogames.sortBy(_.first_release_date))
            case Some("newest") => Right(listOfRequestedVideogames.sortBy(_.first_release_date).reverse)
            case Some("worst_igdb_rated") => Right(listOfRequestedVideogames.sortBy(_.total_rating))
            case Some("best_igdb_rated") => Right(listOfRequestedVideogames.sortBy(_.total_rating).reverse)
            case None => Right(listOfRequestedVideogames)
            case _ => Left(BadRequest("Parameter not supported"))
          }
          IO.pure(sortedVideogames)

        case Left(error: UserError) => IO.pure(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val searchBook: ((String, Option[String])) => IO[Either[UserError, List[SearchedBook]]] =
    (title, sortByOption) =>
      GoogleBooksClient.executeRequest(Books.searchBooksEndpoint, (title, "lite")).flatMap {
        case Right(listOfSearchedBooks: ListOfSearchedBooks) =>
          val listOfBooks = listOfSearchedBooks.items

          val sortedBooks = sortByOption match {
            case Some("oldest") => Right(listOfBooks.sortBy(r => parseDate(r.volumeInfo.publishedDate).map(_.toEpochDay).getOrElse(Long.MaxValue)))
            case Some("newest") => Right(listOfBooks.sortBy(r => parseDate(r.volumeInfo.publishedDate).map(_.toEpochDay).getOrElse(Long.MinValue)).reverse)
            case None => Right(listOfBooks)
            case _ => Left(BadRequest("Parameter not supported"))
          }
          IO.pure(sortedBooks)

        case Left(error: UserError) => IO.pure(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  // TODO: SortBy aquí también, no?
  val searchMediaList: ((String, Option[String])) => IO[Either[UserError, List[MediaList]]] = {
    (title, sortByOption) =>
      val mediaListsObtained = MediaListRepository.findByTitle(title)

      val sortedMediaLists = sortByOption match {
        case Some("oldest") => Right(mediaListsObtained.sortBy(_.creationDate))
        case Some("newest") => Right(mediaListsObtained.sortBy(_.creationDate).reverse)
        case Some("least_recently_updated") => Right(mediaListsObtained.sortBy(_.updateDate))
        case Some("most_recently_updated") => Right(mediaListsObtained.sortBy(_.updateDate).reverse)
        case Some("least_liked") => Right(mediaListsObtained.sortBy(_.likes.size))
        case Some("most_liked") => Right(mediaListsObtained.sortBy(_.likes.size).reverse)
        case Some("least_replied") => Right(mediaListsObtained.sortBy(_.replies.size))
        case Some("most_replied") => Right(mediaListsObtained.sortBy(_.replies.size).reverse)
        case None => Right(mediaListsObtained)
        case _ => Left(BadRequest("Parameter not supported"))
      }

      IO.pure(sortedMediaLists)
        .handleError {
          case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
        }
  }

  // TODO: SortBy aquí también, no?
  val searchUser: ((String, Option[String])) => IO[Either[UserError, List[User]]] = {
    (username, sortByOption) =>
      val usersObtained = UserRepository.findByUsername(username)

      val sortedUsers = sortByOption match {
        case Some("least_popular") => Right(usersObtained.sortBy(_.followers.size))
        case Some("most_popular") => Right(usersObtained.sortBy(_.followers.size).reverse)
        case Some("least_media_lists") => Right(usersObtained.sortBy(_.mediaLists.size))
        case Some("most_media_lists") => Right(usersObtained.sortBy(_.mediaLists.size).reverse)
        case Some("least_reviews") => Right(usersObtained.sortBy(_.reviews.size))
        case Some("most_reviews") => Right(usersObtained.sortBy(_.reviews.size).reverse)
        case Some("least_ratings") => Right(usersObtained.sortBy(_.ratings.size))
        case Some("most_ratings") => Right(usersObtained.sortBy(_.ratings.size).reverse)
        case Some("least_completed_media") => Right(usersObtained.sortBy(_.completed.size))
        case Some("most_completed_media") => Right(usersObtained.sortBy(_.completed.size).reverse)
        case Some("least_dropped_media") => Right(usersObtained.sortBy(_.dropped.size))
        case Some("most_dropped_media") => Right(usersObtained.sortBy(_.dropped.size).reverse)
        case None => Right(usersObtained)
        case _ => Left(BadRequest("Parameter not supported"))
      }

      IO.pure(sortedUsers)
        .handleError {
          case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
        }
  }

}

