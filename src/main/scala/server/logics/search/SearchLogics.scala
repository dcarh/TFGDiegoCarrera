package server.logics.search

import cats.effect.IO
import cats.implicits._
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import dummies.repositories.{MediaListRepository, UserRepository}
import endpoints.googleBooks.Books
import endpoints.igdb.Videogames
import endpoints.tmdb.{Movies, TvShows}
import modelClasses.app.media.{Book, Movie, TvShow, Videogame}
import modelClasses.app.social.MediaList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Media.{BookId, MovieId, TvShowId, VideogameId}
import modelClasses.igdb.VideogameRequests.{RequestedVideogame, VideogameAllFields}
import modelClasses.tmdb.Common.Results
import modelClasses.tmdb.MovieRequests.RequestedMovie
import modelClasses.tmdb.TvShowRequests.RequestedTvShow

object SearchLogics {

  private val tmdbClient = TMDBClient()
  private val igdbClient = IGDBClient()
  private val googleBooksClient = GoogleBooksClient()

  val searchMovie: ((String, Option[String])) => IO[Either[UserError, List[Movie]]] =
    (title, sortByOption) =>
      tmdbClient.executeRequest(Movies.searchMoviesEndpoint, title).flatMap {
        case Right(results: Results) =>
          results.results.traverse { result =>
            tmdbClient.executeRequest(Movies.requestMovieEndpoint, MovieId(result.id)).flatMap {
              case Right(movie: RequestedMovie) => IO(Movie(requestedMovie = movie))
              case Left(error) => IO(Left(error))
            }
          }.flatMap { listOfEither =>
            val listOfMovies = listOfEither.collect { case movie: Movie => movie }

            val sortedMovies: Either[UserError, List[Movie]] = sortByOption match {
              case Some("shortest") => Right(listOfMovies.sortBy(_.requestedMovie.runtime))
              case Some("longest") => Right(listOfMovies.sortBy(_.requestedMovie.runtime).reverse)
              case Some("oldest") => Right(listOfMovies.sortBy(_.requestedMovie.release_date))
              case Some("newest") => Right(listOfMovies.sortBy(_.requestedMovie.release_date).reverse)
              case None => Right(listOfMovies)
              case _ => Left(BadRequest("Parameter not supported"))
            }
            IO(sortedMovies)
          }

        case Left(error) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val searchTvShow: ((String, Option[String])) => IO[Either[UserError, List[TvShow]]] =
    (title, sortByOption) =>
      tmdbClient.executeRequest(TvShows.searchTvShowsEndpoint, title).flatMap {
        case Right(results: Results) =>
          results.results.traverse { result =>
            tmdbClient.executeRequest(TvShows.requestTvShowEndpoint, MovieId(result.id)).flatMap {
              case Right(tvShow: RequestedTvShow) => IO(TvShow(requestedTvShow = tvShow))
              case Left(error) => IO(Left(error))
            }
          }.flatMap { listOfEither =>
            val listOfTvShows = listOfEither.collect { case tvShow: TvShow => tvShow }

            val sortedTvShows: Either[UserError, List[TvShow]] = sortByOption match {
              case Some("oldest") => Right(listOfTvShows.sortBy(_.requestedTvShow.first_air_date))
              case Some("newest") => Right(listOfTvShows.sortBy(_.requestedTvShow.first_air_date).reverse)
              case None => Right(listOfTvShows)
              case _ => Left(BadRequest("Parameter not supported"))
            }
            IO(sortedTvShows)
          }

        case Left(error) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  // TODO: Seguir por aquí, comprobar si va bien o no
  val searchVideogame: ((String, Option[String])) => IO[Either[UserError, List[Videogame]]] =
    (title, sortByOption) =>
      igdbClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, title).flatMap {
        case Right(requestedListOfVideogames: List[VideogameAllFields]) =>
          val listOfVideogames = requestedListOfVideogames.map(
            requestedVideogame =>
              Videogame(
                requestedVideogame = requestedVideogame
//                category = requestedVideogame.category,
//                collection = requestedVideogame.collection,
//                collections = requestedVideogame.collections,
//                dlcs = requestedVideogame.dlcs,
//                externalGames = requestedVideogame.external_games,
//                firstReleaseDate = requestedVideogame.first_release_date,
//                franchises = requestedVideogame.franchises,
//                gameEngines = requestedVideogame.game_engines,
//                gameModes = requestedVideogame.game_modes,
//                genres = requestedVideogame.genres,
//                id = VideogameId(requestedVideogame.id),
//                involvedCompanies = requestedVideogame.involved_companies,
//                parentGame = requestedVideogame.parent_game,
//                platforms = requestedVideogame.platforms,
//                playerPerspectives = requestedVideogame.player_perspectives,
//                remakes = requestedVideogame.remakes,
//                similarGames = requestedVideogame.similar_games,
//                standaloneExpansions = requestedVideogame.standalone_expansions,
//                storyLine = requestedVideogame.storyline,
//                summary = requestedVideogame.summary,
//                themes = requestedVideogame.themes,
//                title = requestedVideogame.name,
//                year = requestedVideogame.first_release_date // Extraer el año de release_date
              )
          )

          // TODO: Lo mismo que con las Movies
          val sortedVideogames = sortByOption match {
            case Some("oldest") => Right(listOfVideogames.sortBy(_.requestedVideogame.first_release_date))
            case Some("newest") => Right(listOfVideogames.sortBy(_.requestedVideogame.first_release_date).reverse)
            case None => Right(listOfVideogames)
            case _ => Left(BadRequest("Parameter not supported"))
          }
          IO(sortedVideogames)

        case Left(error: UserError) => IO(Left(error))
      }

//  val searchBook: ((String, Option[String])) => IO[Either[UserError, List[Book]]] = ???

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

