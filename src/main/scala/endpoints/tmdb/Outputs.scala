package endpoints.tmdb

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.tmdb.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object Outputs {
  

  object MoviesOutputs {
    
    import MovieRequests.*

    val jsonRequestedMovieOut: EndpointOutput[RequestedMovie] =
      jsonBody[RequestedMovie]

    val jsonRequestedSimilarMoviesOut: EndpointOutput[RequestedSimilarMovies] =
      jsonBody[RequestedSimilarMovies]

    val jsonRequestedRecommendedMoviesOut: EndpointOutput[RequestedRecommendedMovies] =
      jsonBody[RequestedRecommendedMovies]

    val jsonRequestedCreditsForMovieOut: EndpointOutput[RequestedCreditsForMovie] =
      jsonBody[RequestedCreditsForMovie]
  }
  
  object TVShowsOutputs {

    import TVShowRequests.*
    
    val jsonRequestedTvShowOut: EndpointOutput[RequestedTVShow] =
      jsonBody[RequestedTVShow]

    val jsonRequestedSimilarTvShowsOut: EndpointOutput[RequestedSimilarTVShows] =
      jsonBody[RequestedSimilarTVShows]

    val jsonRequestedRecommendedTvShowsOut: EndpointOutput[RequestedRecommendedTVShows] =
      jsonBody[RequestedRecommendedTVShows]

    val jsonRequestedCreditsForTvShowOut: EndpointOutput[RequestedCreditsForTVShow] =
      jsonBody[RequestedCreditsForTVShow]

    val jsonRequestedAggregateCreditsForTvShowOut: EndpointOutput[RequestedAggregateCreditsForTVShow] =
      jsonBody[RequestedAggregateCreditsForTVShow]
  }
  
  object SeasonsOutputs {
    
    import SeasonRequests.*
    
    val jsonRequestedSeasonOut: EndpointOutput[RequestedSeason] =
      jsonBody[RequestedSeason]

    val jsonRequestedCreditsForSeasonOut: EndpointOutput[RequestedCreditsForSeason] =
      jsonBody[RequestedCreditsForSeason]

    val jsonRequestedAggregateCreditsForSeasonOut: EndpointOutput[RequestedAggregateCreditsForSeason] =
      jsonBody[RequestedAggregateCreditsForSeason]
  }
  
  object EpisodesOutputs {
    
    import EpisodeRequests.*

    val jsonRequestedEpisodeOut: EndpointOutput[RequestedEpisode] =
      jsonBody[RequestedEpisode]

    val jsonRequestedCreditsForEpisodeOut: EndpointOutput[RequestedCreditsForEpisode] =
      jsonBody[RequestedCreditsForEpisode]
  }
}
