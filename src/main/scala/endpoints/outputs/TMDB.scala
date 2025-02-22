package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.tmdb.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object TMDB {

  object MoviesOutputs {

    import MovieRequests.*

    val jsonRequestedMovieOut: EndpointOutput[RequestedMovie] =
      jsonBody[RequestedMovie]

    val jsonRequestedMoviesListOut: EndpointOutput[List[RequestedMovie]] =
      jsonBody[List[RequestedMovie]]

    val jsonRequestedSimilarMoviesOut: EndpointOutput[RequestedSimilarMovies] =
      jsonBody[RequestedSimilarMovies]

    val jsonRequestedRecommendedMoviesOut: EndpointOutput[RequestedRecommendedMovies] =
      jsonBody[RequestedRecommendedMovies]

    val jsonRequestedCreditsForMovieOut: EndpointOutput[RequestedCreditsForMovie] =
      jsonBody[RequestedCreditsForMovie]
  }

  object TVShowsOutputs {

    import TvShowRequests.*

    val jsonRequestedTvShowOut: EndpointOutput[RequestedTVShow] =
      jsonBody[RequestedTVShow]

    val jsonRequestedTvShowsListOut: EndpointOutput[List[RequestedTVShow]] =
      jsonBody[List[RequestedTVShow]]

    val jsonRequestedSimilarTvShowsOut: EndpointOutput[RequestedSimilarTvShows] =
      jsonBody[RequestedSimilarTvShows]

    val jsonRequestedRecommendedTvShowsOut: EndpointOutput[RequestedRecommendedTvShows] =
      jsonBody[RequestedRecommendedTvShows]

    val jsonRequestedCreditsForTvShowOut: EndpointOutput[RequestedCreditsForTvShow] =
      jsonBody[RequestedCreditsForTvShow]

    val jsonRequestedAggregateCreditsForTvShowOut: EndpointOutput[RequestedAggregateCreditsForTvShow] =
      jsonBody[RequestedAggregateCreditsForTvShow]
  }

  object SeasonsOutputs {

    import TvSeasonRequests.*

    val jsonRequestedTvSeasonOut: EndpointOutput[RequestedTvSeason] =
      jsonBody[RequestedTvSeason]

    val jsonRequestedCreditsForTvSeasonOut: EndpointOutput[RequestedCreditsForTvSeason] =
      jsonBody[RequestedCreditsForTvSeason]

    val jsonRequestedAggregateCreditsForTvSeasonOut: EndpointOutput[RequestedAggregateCreditsForTvSeason] =
      jsonBody[RequestedAggregateCreditsForTvSeason]
  }

  object EpisodesOutputs {

    import TvEpisodeRequests.*

    val jsonRequestedTvEpisodeOut: EndpointOutput[RequestedTvEpisode] =
      jsonBody[RequestedTvEpisode]

    val jsonRequestedCreditsForTvEpisodeOut: EndpointOutput[RequestedCreditsForTvEpisode] =
      jsonBody[RequestedCreditsForTvEpisode]
  }

}
