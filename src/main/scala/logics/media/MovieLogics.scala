package logics.media

import cats.effect.IO
import clients.TMDBClient
import endpoints.apis.tmdb.Movies
import memory.repositories.{EntryRepository, MediaListRepository}
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.errors.UserError.*
import domain.ids.Media.MovieId
import domain.ids.Social.{EntryId, MediaListId}
import domain.apis.tmdb.MovieRequests.MovieFromTMDB
import logics.functions.MediaAuxFunctions.getMetricsForMedia

object MovieLogics {

  val getMovie: MovieId => IO[Either[UserError, Movie]] = 
    movieId =>
      TMDBClient.executeRequest(Movies.requestMovie, movieId).flatMap {
        case Left(error)           => IO.pure(Left(error))
        case Right(requestedMovie) =>
          val similarMoviesTmdb = TMDBClient.executeRequest(Movies.requestedSimilarMovies, movieId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting similar movies: ${ex.getMessage}")
                None
            }

          val recommendedMoviesTmdb = TMDBClient.executeRequest(Movies.requestedRecommendedMovies, movieId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting recommended movies: ${ex.getMessage}")
                None
            }

          val creditsTmdb = TMDBClient.executeRequest(Movies.requestedCreditsForMovie, movieId)
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting credits for the movie: ${ex.getMessage}")
                None
            }

          val metrics = getMetricsForMedia(movieId)

          for {
            similarMovies     <- similarMoviesTmdb
            recommendedMovies <- recommendedMoviesTmdb
            credits           <- creditsTmdb
          } yield Right(Movie(
            movieFromTMDB     = requestedMovie,
            similarMovies     = similarMovies,
            recommendedMovies = recommendedMovies,
            cast              = credits.map(_.cast),
            crew              = credits.map(_.crew),
            averageRating     = metrics.averageRating,
            entriesIds        = metrics.entriesIds,
            mediaListsIds     = metrics.mediaListsIds,
            completedCount    = metrics.statusCounts.completed,
            droppedCount      = metrics.statusCounts.dropped,
            pendingCount      = metrics.statusCounts.pending,
            totalRatings      = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForMovie: MovieId => IO[Either[UserError, List[Entry]]] =
    movieId =>
      IO.pure {
        val entries = EntryRepository.getAll.filter(_.mediaId == movieId)

        Right(entries)
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getMediaListsForMovie: MovieId => IO[Either[UserError, List[MediaList]]] =
    movieId =>
      IO.pure {
        val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains(movieId))

        Right(mediaLists)

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }


}

