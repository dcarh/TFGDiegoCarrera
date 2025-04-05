package server.logics.media

import cats.effect.IO
import cats.effect.IO.{IOCont, Uncancelable}
import cats.implicits.*
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import endpoints.igdb.Videogames
import endpoints.googleBooks.Books
import endpoints.tmdb.{Movies, TvEpisodes, TvSeasons, TvShows}
import modelClasses.app.media.*
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Media.*
import modelClasses.igdb.VideogameRequests.VideogameAllFields
import modelClasses.tmdb.Common.{Credits, Results}
import modelClasses.tmdb.MovieRequests.RequestedMovie
import server.logics.media.MediaAuxFunctions.{getAverageRatingForMedia, getEntriesIdsForMedia, getListsIdsForMedia, getStatusCountForMedia, getTotalRatingsForMedia}

object MediaLogics {

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
  
          // TODO: Calcular atributos de la Movie de dentro de la app (listas, votos, etc)
          val completedTimes  = getStatusCountForMedia(movieId, "completed")
          val droppedTimes    = getStatusCountForMedia(movieId, "dropped")
          val pendingTimes    = getStatusCountForMedia(movieId, "pending")
          
          val averageRating   = getAverageRatingForMedia(movieId)
          val totalRatings    = getTotalRatingsForMedia(movieId)
          
          val listsIds        = getListsIdsForMedia(movieId)
          val entriesIds      = getEntriesIdsForMedia(movieId)
  
          for {
            similarMovies <- similarMoviesTmdb
            recommendedMovies <- recommendedMoviesTmdb
            credits <- creditsTmdb
          } yield Right(Movie(
            requestedMovie = requestedMovie,
            similarMovies = similarMovies,
            recommendedMovies = recommendedMovies,
            cast = credits.map(_.cast),
            crew = credits.map(_.crew),
            averageRating = averageRating,
            entriesIds = entriesIds,
            listsIds = listsIds,
            numberOfCompleted = completedTimes,
            numberOfDropped = droppedTimes,
            numberOfPending = pendingTimes,
            totalRatings = totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          println(s"Unexpected error: ${ex.getMessage}")
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }


  val getTvShow: TvShowId => IO[Either[UserError, TvShow]] =
    tvShowId =>
      TMDBClient.executeRequest(TvShows.requestTvShowEndpoint, tvShowId).flatMap {
        case Left(error) => IO.pure(Left(error))
        case Right(requestedTvShow) =>
          val similarTvShowsTmdb = TMDBClient.executeRequest(TvShows.requestedSimilarTvShowsEndpoint, tvShowId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting similar TV shows: ${ex.getMessage}")
                None
            }

          val recommendedTvShowsTmdb = TMDBClient.executeRequest(TvShows.requestedRecommendedTvShowsEndpoint, tvShowId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting recommended TV shows: ${ex.getMessage}")
                None
            }

          val aggregateCreditsTmdb = TMDBClient.executeRequest(TvShows.requestedAggregateCreditsForTvShowEndpoint, tvShowId)
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting aggregate credits for the TV show: ${ex.getMessage}")
                None
            }

          // TODO: Calcular atributos de la Movie de dentro de la app (listas, votos, etc)
          val completedTimes  = getStatusCountForMedia(tvShowId, "completed")
          val droppedTimes    = getStatusCountForMedia(tvShowId, "dropped")
          val inProgressTimes = getStatusCountForMedia(tvShowId, "inProgress")
          val onHoldTimes     = getStatusCountForMedia(tvShowId, "onHold")
          val pendingTimes    = getStatusCountForMedia(tvShowId, "pending")
          
          val averageRating   = getAverageRatingForMedia(tvShowId)
          val totalRatings    = getTotalRatingsForMedia(tvShowId)

          val listsIds        = getListsIdsForMedia(tvShowId)
          val entriesIds      = getEntriesIdsForMedia(tvShowId)

          for {
            similarTvShows <- similarTvShowsTmdb
            recommendedTvShows <- recommendedTvShowsTmdb
            aggregateCredits <- aggregateCreditsTmdb
          } yield Right(TvShow(
            requestedTvShow = requestedTvShow,
            similarTvShows = similarTvShows,
            recommendedTvShows = recommendedTvShows,
            cast = aggregateCredits.map(_.cast),        // TODO: Ordenarlos según el campo "order" que hay dentro de Member
            crew = aggregateCredits.map(_.crew),
            averageRating = averageRating,
            entriesIds = entriesIds,
            listsIds = listsIds,
            numberOfCompleted = completedTimes,
            numberOfDropped = droppedTimes,
            numberOfInProgress = inProgressTimes,
            numberOfOnHold = onHoldTimes,
            numberOfPending = pendingTimes,
            totalRatings = totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          println(s"Unexpected error: ${ex.getMessage}")
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  
  val getTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, TvSeason]] =
    (tvShowId, tvSeasonNumber) =>
      TMDBClient.executeRequest(TvSeasons.requestTvSeasonEndpoint, (tvShowId, tvSeasonNumber)).flatMap {
        case Left(error) => IO.pure(Left(error))
        case Right(requestedTvSeason) =>
          val aggregateCreditsTmdb = TMDBClient.executeRequest(TvSeasons.requestedAggregateCreditsForTvSeasonEndpoint, (tvShowId, tvSeasonNumber))
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting aggregate credits for the TV season: ${ex.getMessage}")
                None
            }

          // TODO: Calcular atributos de la Movie de dentro de la app (listas, votos, etc)
          val completedTimes  = getStatusCountForMedia((tvShowId, tvSeasonNumber), "completed")
          val droppedTimes    = getStatusCountForMedia((tvShowId, tvSeasonNumber), "dropped")
          val inProgressTimes = getStatusCountForMedia((tvShowId, tvSeasonNumber), "inProgress")
          val onHoldTimes     = getStatusCountForMedia((tvShowId, tvSeasonNumber), "onHold")
          val pendingTimes    = getStatusCountForMedia((tvShowId, tvSeasonNumber), "pending")
          
          val averageRating   = getAverageRatingForMedia((tvShowId, tvSeasonNumber))
          val totalRatings    = getTotalRatingsForMedia((tvShowId, tvSeasonNumber))

          val listsIds        = getListsIdsForMedia((tvShowId, tvSeasonNumber))
          val entriesIds      = getEntriesIdsForMedia((tvShowId, tvSeasonNumber))

          for {
            aggregateCredits <- aggregateCreditsTmdb
          } yield Right(TvSeason(
            requestedTvSeason = requestedTvSeason,
            cast = aggregateCredits.map(_.cast), // TODO: Ordenarlos según el campo "order" que hay dentro de Member
            crew = aggregateCredits.map(_.crew),
            averageRating = averageRating,
            entriesIds = entriesIds,
            listsIds = listsIds,
            numberOfCompleted = completedTimes,
            numberOfDropped = droppedTimes,
            numberOfInProgress = inProgressTimes,
            numberOfOnHold = onHoldTimes,
            numberOfPending = pendingTimes,
            totalRatings = totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          println(s"Unexpected error: ${ex.getMessage}")
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, TvEpisode]] =
    (tvShowId, tvSeasonNumber, tvEpisodeNumber) =>
      TMDBClient.executeRequest(TvEpisodes.requestTvEpisodeEndpoint, (tvShowId, tvSeasonNumber, tvEpisodeNumber)).flatMap {
        case Left(error) => IO.pure(Left(error))
        case Right(requestedTvEpisode) =>
          val creditsTmdb = TMDBClient.executeRequest(TvEpisodes.requestedCreditsForTvEpisodeEndpoint, (tvShowId, tvSeasonNumber, tvEpisodeNumber))
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting aggregate credits for the TV episode: ${ex.getMessage}")
                None
            }

          // TODO: Calcular atributos de la Movie de dentro de la app (listas, votos, etc)
          val completedTimes = getStatusCountForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber), "completed")
          val droppedTimes   = getStatusCountForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber), "dropped")
          val pendingTimes   = getStatusCountForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber), "pending")
          
          val averageRating  = getAverageRatingForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber))
          val totalRatings   = getTotalRatingsForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber))

          val listsIds       = getListsIdsForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber))
          val entriesIds     = getEntriesIdsForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber))

          for {
            credits <- creditsTmdb
          } yield Right(TvEpisode(
            requestedTvEpisode = requestedTvEpisode,
            cast = credits.map(_.cast),               // TODO: Ordenarlos según el campo "order" que hay dentro de Member
            crew = credits.map(_.crew),
            averageRating = averageRating,
            entriesIds = entriesIds,
            listsIds = listsIds,
            numberOfCompleted = completedTimes,
            numberOfDropped = droppedTimes,
            numberOfPending = pendingTimes,
            totalRatings = totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          println(s"Unexpected error: ${ex.getMessage}")
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getVideogame: VideogameId => IO[Either[UserError, Videogame]] =
    videogameId =>
      IGDBClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, videogameId).flatMap {
        case Right(requestedListOfVideogames: List[VideogameAllFields]) =>
          requestedListOfVideogames match
            case head :: tail =>
              val completedTimes  = getStatusCountForMedia(videogameId, "completed")
              val droppedTimes    = getStatusCountForMedia(videogameId, "dropped")
              val inProgressTimes = getStatusCountForMedia(videogameId, "inProgress")
              val onHoldTimes     = getStatusCountForMedia(videogameId, "onHold")
              val pendingTimes    = getStatusCountForMedia(videogameId, "pending")
              
              val averageRating   = getAverageRatingForMedia(videogameId)
              val totalRatings    = getTotalRatingsForMedia(videogameId)

              val listsIds        = getListsIdsForMedia(videogameId)
              val entriesIds      = getEntriesIdsForMedia(videogameId)
              
              IO.pure(Right(
                Videogame(
                  requestedVideogame = head,
                  averageRating = averageRating,
                  entriesIds = entriesIds,
                  listsIds = listsIds,
                  numberOfCompleted = completedTimes, 
                  numberOfDropped = droppedTimes, 
                  numberOfInProgress = inProgressTimes, 
                  numberOfOnHold = onHoldTimes, 
                  numberOfPending = pendingTimes,
                  totalRatings = totalRatings
                )
              ))
            case Nil => IO.pure(Left(NotFound("Not found videogame with ID introduced")))

        case Left(error: UserError) => IO(Left(error))
          
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getBook: BookId => IO[Either[UserError, Book]] =
    bookId =>
      GoogleBooksClient.executeRequest(Books.requestBookEndpoint, bookId).flatMap {
        case Right(requestedBook: RequestedBook) =>
          val completedTimes  = getStatusCountForMedia(bookId, "completed")
          val droppedTimes    = getStatusCountForMedia(bookId, "dropped")
          val inProgressTimes = getStatusCountForMedia(bookId, "inProgress")
          val onHoldTimes     = getStatusCountForMedia(bookId, "onHold")
          val pendingTimes    = getStatusCountForMedia(bookId, "pending")
          
          val averageRating   = getAverageRatingForMedia(bookId)
          val totalRatings    = getTotalRatingsForMedia(bookId)

          val listsIds        = getListsIdsForMedia(bookId)
          val entriesIds      = getEntriesIdsForMedia(bookId)
          
          IO.pure(Right(
            Book(
              requestedBook = requestedBook,
              averageRating = averageRating,
              entriesIds = entriesIds,
              listsIds = listsIds,
              numberOfCompleted = completedTimes, 
              numberOfDropped = droppedTimes, 
              numberOfInProgress = inProgressTimes, 
              numberOfOnHold = onHoldTimes, 
              numberOfPending = pendingTimes,
              totalRatings = totalRatings
            )
          ))

        case Left(error: UserError) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

}

