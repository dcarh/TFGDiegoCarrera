package server.logics

import cats.effect.IO
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import dummies.repositories.{MediaContentListRepository, UserRepository}
import endpoints.googleBooks.Books
import endpoints.igdb.Videogames
import endpoints.tmdb.{Movies, TVShows}
import modelClasses.app.media.{Book, Movie, TvShow, Videogame}
import modelClasses.app.social.MediaContentList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, MovieId, TvShowId, VideogameId}
import modelClasses.tmdb.MovieRequests.RequestedMovie
import modelClasses.tmdb.TVShowRequests.RequestedTVShow
import modelClasses.igdb.VideogameRequests.{RequestedVideogame, VideogameAllFields}
import modelClasses.googleBooks.BooksRequests.RequestedBook

object SearchLogics {

  private val tmdbClient = TMDBClient()
  private val igdbClient = IGDBClient()
  private val googleBooksClient = GoogleBooksClient()

  val searchMovie: ((String, Option[String])) => IO[Either[UserError, List[Movie]]] =
    (title, sortByOption) =>
      tmdbClient.executeRequest(Movies.searchMoviesEndpoint, title).flatMap {
        case Right(requestedListOfMovies: List[RequestedMovie]) =>
          val listOfMovies = requestedListOfMovies.map { requestedMovie =>
            Movie(
              budget = requestedMovie.budget,
              id = MovieId(requestedMovie.id),
              overview = requestedMovie.overview,
              release_date = requestedMovie.release_date,
              revenue = requestedMovie.revenue,
              runtime = requestedMovie.runtime,
              status = requestedMovie.status,
              title = requestedMovie.title,
              year = requestedMovie.release_date.split("-")(0)
            )
          }

          val sortedMovies: Either[UserError, List[Movie]] = sortByOption match {
            case Some("shortest") => Right(listOfMovies.sortBy(_.runtime))
            case Some("longest") => Right(listOfMovies.sortBy(_.runtime).reverse)
            case Some("oldest") => Right(listOfMovies.sortBy(_.release_date))
            case Some("newest") => Right(listOfMovies.sortBy(_.release_date).reverse)
            case None => Right(listOfMovies)
            case _ => Left(BadRequest("Parameter not supported"))
          }
          IO(sortedMovies)

        case Left(error: UserError) => IO(Left(error))

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val searchTVShow: ((String, Option[String])) => IO[Either[UserError, List[TvShow]]] =
    (title, sortByOption) =>
      tmdbClient.executeRequest(TVShows.searchTvShowsEndpoint, title).flatMap {
        case Right(requestedListOfTvShows: List[RequestedTVShow]) =>
          val listOfTvShows = requestedListOfTvShows.map(
            requestedTvShow =>
              TvShow(
                firstAirDate = requestedTvShow.first_air_date,
                id = TvShowId(requestedTvShow.id),
                lastAirDate = requestedTvShow.last_air_date,
                numberOfEpisodes = requestedTvShow.number_of_episodes,
                numberOfSeasons = requestedTvShow.number_of_seasons,
                overview = requestedTvShow.overview,
                status = requestedTvShow.status,
                title = requestedTvShow.name,
                year = requestedTvShow.first_air_date.split("-")(0) // Extraer el año de release_date
              )
          )
          val sortedTvShows = sortByOption match {
            case Some("oldest") => Right(listOfTvShows.sortBy(_.firstAirDate))
            case Some("newest") => Right(listOfTvShows.sortBy(_.firstAirDate).reverse)
            case None => Right(listOfTvShows)
          }
          IO(sortedTvShows)

        case Left(error: UserError) => IO(Left(error))

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val searchVideogame: ((String, Option[String])) => IO[Either[UserError, List[Videogame]]] =
    (title, sortByOption) =>
      igdbClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, title).flatMap {
        case Right(requestedListOfVideogames: List[VideogameAllFields]) =>
          val listOfVideogames = requestedListOfVideogames.map(
            requestedVideogame =>
              Videogame(
                category = requestedVideogame.category,
                collection = requestedVideogame.collection,
                collections = requestedVideogame.collections,
                dlcs = requestedVideogame.dlcs,
                externalGames = requestedVideogame.external_games,
                firstReleaseDate = requestedVideogame.first_release_date,
                franchises = requestedVideogame.franchises,
                gameEngines = requestedVideogame.game_engines,
                gameModes = requestedVideogame.game_modes,
                genres = requestedVideogame.genres,
                id = VideogameId(requestedVideogame.id),
                involvedCompanies = requestedVideogame.involved_companies,
                parentGame = requestedVideogame.parent_game,
                platforms = requestedVideogame.platforms,
                playerPerspectives = requestedVideogame.player_perspectives,
                remakes = requestedVideogame.remakes,
                similarGames = requestedVideogame.similar_games,
                standaloneExpansions = requestedVideogame.standalone_expansions,
                storyLine = requestedVideogame.storyline,
                summary = requestedVideogame.summary,
                themes = requestedVideogame.themes,
                title = requestedVideogame.name,
                year = requestedVideogame.first_release_date // Extraer el año de release_date
              )
          )
          val sortedVideogames = sortByOption match {
            case Some("oldest") => Right(listOfVideogames.sortBy(_.firstReleaseDate))
            case Some("newest") => Right(listOfVideogames.sortBy(_.firstReleaseDate).reverse)
            case None => Right(listOfVideogames)
          }
          IO(sortedVideogames)

        case Left(error: UserError) => IO(Left(error))
      }

//  val searchBook: ((String, Option[String])) => IO[Either[UserError, List[Book]]] = ???

  val searchMediaContentList: String => IO[Either[UserError, List[MediaContentList]]] = {
    title =>
      IO(Right(MediaContentListRepository.findByTitle(title)))
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

