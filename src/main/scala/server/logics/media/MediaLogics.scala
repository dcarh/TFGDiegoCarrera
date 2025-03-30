package server.logics.media

import cats.effect.IO
import cats.effect.IO.{IOCont, Uncancelable}
import cats.implicits.*
import clients.{GoogleBooksClient, IGDBClient, TMDBClient}
import endpoints.tmdb.{Movies, TvEpisodes, TvSeasons, TvShows}
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.*
import modelClasses.app.media.*
import modelClasses.tmdb.Common.{Credits, Results}
import modelClasses.tmdb.MovieRequests.RequestedMovie

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
  
          for {
            similarMovies <- similarMoviesTmdb
            recommendedMovies <- recommendedMoviesTmdb
            credits <- creditsTmdb
          } yield Right(Movie(
            requestedMovie = requestedMovie,
            similarMovies = similarMovies,
            recommendedMovies = recommendedMovies,
            cast = credits.map(_.cast),
            crew = credits.map(_.crew)
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

          for {
            similarTvShows <- similarTvShowsTmdb
            recommendedTvShows <- recommendedTvShowsTmdb
            aggregateCredits <- aggregateCreditsTmdb
          } yield Right(TvShow(
            requestedTvShow = requestedTvShow,
            similarTvShows = similarTvShows,
            recommendedTvShows = recommendedTvShows,
            cast = aggregateCredits.map(_.cast),        // TODO: Ordenarlos según el campo "order" que hay dentro de Member
            crew = aggregateCredits.map(_.crew)
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

          for {
            aggregateCredits <- aggregateCreditsTmdb
          } yield Right(TvSeason(
            requestedTvSeason = requestedTvSeason,
            cast = aggregateCredits.map(_.cast), // TODO: Ordenarlos según el campo "order" que hay dentro de Member
            crew = aggregateCredits.map(_.crew)
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

          for {
            credits <- creditsTmdb
          } yield Right(TvEpisode(
            requestedTvEpisode = requestedTvEpisode,
            cast = credits.map(_.cast),               // TODO: Ordenarlos según el campo "order" que hay dentro de Member
            crew = credits.map(_.crew)
          ))
      }.handleError {
        case ex: Exception =>
          println(s"Unexpected error: ${ex.getMessage}")
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

//  val getVideogame: VideogameId => IO[Either[UserError, Videogame]] = ???
//
//  val getBook: BookId => IO[Either[UserError, Book]] = ???

}

