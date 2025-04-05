package server.logics.media

import cats.effect.IO
import cats.implicits.*
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import endpoints.igdb.Videogames
import endpoints.googleBooks.Books
import endpoints.tmdb.{Movies, TvEpisodes, TvSeasons, TvShows}
import modelClasses.app.media.*
import modelClasses.errors.UserError.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Media.*
import modelClasses.igdb.VideogameRequests.VideogameAllFields
import modelClasses.tmdb.MovieRequests.RequestedMovie
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

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

          val metrics = getMetricsForMedia(tvShowId)

          for {
            similarTvShows     <- similarTvShowsTmdb
            recommendedTvShows <- recommendedTvShowsTmdb
            aggregateCredits   <- aggregateCreditsTmdb
          } yield Right(TvShow(
            requestedTvShow    = requestedTvShow,
            similarTvShows     = similarTvShows,
            recommendedTvShows = recommendedTvShows,
            cast               = aggregateCredits.map(_.cast.sortBy(_.order)),
            crew               = aggregateCredits.map(_.crew),
            averageRating      = metrics.averageRating,
            entriesIds         = metrics.entriesIds,
            listsIds           = metrics.listsIds,
            numberOfCompleted  = metrics.statusCounts.completed,
            numberOfDropped    = metrics.statusCounts.dropped,
            numberOfInProgress = metrics.statusCounts.inProgress,
            numberOfOnHold     = metrics.statusCounts.onHold,
            numberOfPending    = metrics.statusCounts.pending,
            totalRatings       = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
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

          val metrics = getMetricsForMedia((tvShowId, tvSeasonNumber))

          for {
            aggregateCredits <- aggregateCreditsTmdb
          } yield Right(TvSeason(
            requestedTvSeason  = requestedTvSeason,
            cast               = aggregateCredits.map(_.cast.sortBy(_.order)),
            crew               = aggregateCredits.map(_.crew),
            averageRating      = metrics.averageRating,
            entriesIds         = metrics.entriesIds,
            listsIds           = metrics.listsIds,
            numberOfCompleted  = metrics.statusCounts.completed,
            numberOfDropped    = metrics.statusCounts.dropped,
            numberOfInProgress = metrics.statusCounts.inProgress,
            numberOfOnHold     = metrics.statusCounts.onHold,
            numberOfPending    = metrics.statusCounts.pending,
            totalRatings       = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
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

          val metrics = getMetricsForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber))

          for {
            credits <- creditsTmdb
          } yield Right(TvEpisode(
            requestedTvEpisode = requestedTvEpisode,
            cast               = credits.map(_.cast.sortBy(_.order)),
            crew               = credits.map(_.crew),
            averageRating      = metrics.averageRating,
            entriesIds         = metrics.entriesIds,
            listsIds           = metrics.listsIds,
            numberOfCompleted  = metrics.statusCounts.completed,
            numberOfDropped    = metrics.statusCounts.dropped,
            numberOfPending    = metrics.statusCounts.pending,
            totalRatings       = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getVideogame: VideogameId => IO[Either[UserError, Videogame]] =
    videogameId =>
      IGDBClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, videogameId).flatMap {
        case Right(requestedListOfVideogames: List[VideogameAllFields]) =>
          requestedListOfVideogames match
            case head :: tail =>
              val metrics = getMetricsForMedia(videogameId)
              
              IO.pure(Right(
                Videogame(
                  requestedVideogame = head,
                  averageRating      = metrics.averageRating,
                  entriesIds         = metrics.entriesIds,
                  listsIds           = metrics.listsIds,
                  numberOfCompleted  = metrics.statusCounts.completed,
                  numberOfDropped    = metrics.statusCounts.dropped,
                  numberOfInProgress = metrics.statusCounts.inProgress,
                  numberOfOnHold     = metrics.statusCounts.onHold,
                  numberOfPending    = metrics.statusCounts.pending,
                  totalRatings       = metrics.totalRatings
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
          val metrics = getMetricsForMedia(bookId)
          
          IO.pure(Right(
            Book(
              requestedBook      = requestedBook,
              averageRating      = metrics.averageRating,
              entriesIds         = metrics.entriesIds,
              listsIds           = metrics.listsIds,
              numberOfCompleted  = metrics.statusCounts.completed,
              numberOfDropped    = metrics.statusCounts.dropped,
              numberOfInProgress = metrics.statusCounts.inProgress,
              numberOfOnHold     = metrics.statusCounts.onHold,
              numberOfPending    = metrics.statusCounts.pending,
              totalRatings       = metrics.totalRatings
            )
          ))

        case Left(error: UserError) => IO(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

}

