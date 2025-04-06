package server.logics.media

import cats.effect.IO
import clients.TMDBClient
import dummies.repositories.{EntryRepository, MediaListRepository}
import endpoints.tmdb.Movies
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.MovieId
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.tmdb.MovieRequests.RequestedMovie
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

object MovieLogics {

  val getMovie: MovieId => IO[Either[UserError, Movie]] = 
    movieId =>
      TMDBClient.executeRequest(Movies.requestMovieEndpoint, movieId).flatMap {
        case Left(error) => IO.pure(Left(error))
        case Right(requestedMovie) =>
          val similarMoviesTmdb = TMDBClient.executeRequest(Movies.requestedSimilarMoviesEndpoint, movieId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting similar movies: ${ex.getMessage}")
                None
            }

          val recommendedMoviesTmdb = TMDBClient.executeRequest(Movies.requestedRecommendedMoviesEndpoint, movieId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting recommended movies: ${ex.getMessage}")
                None
            }

          val creditsTmdb = TMDBClient.executeRequest(Movies.requestedCreditsForMovieEndpoint, movieId)
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
            requestedMovie    = requestedMovie,
            similarMovies     = similarMovies,
            recommendedMovies = recommendedMovies,
            cast              = credits.map(_.cast),
            crew              = credits.map(_.crew),
            averageRating     = metrics.averageRating,
            entriesIds        = metrics.entriesIds,
            listsIds          = metrics.listsIds,
            numberOfCompleted = metrics.statusCounts.completed,
            numberOfDropped   = metrics.statusCounts.dropped,
            numberOfPending   = metrics.statusCounts.pending,
            totalRatings      = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForMovie: ((MovieId, List[EntryId])) => IO[Either[UserError, List[Entry]]] =
    (_, entriesIds) => 
      val entries = EntryRepository.getMany(entriesIds)
      
      if (entries.nonEmpty) 
        IO.pure(Right(entries))
      else 
        IO.pure(Left(BadRequest("No entries found for such IDs")))

  val getMediaListsForMovie: ((MovieId, List[MediaListId])) => IO[Either[UserError, List[MediaList]]] =
    (_, mediaListsIds) =>
      val mediaLists = MediaListRepository.getMany(mediaListsIds)

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for such IDs")))


}

