package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.tmdb.*
import modelClasses.tmdb.Common.{Credits, Results}
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object TMDB {
  
  val jsonResultsOut: EndpointOutput[Results] =
    jsonBody[Results]
    
  val jsonCreditsOut: EndpointOutput[Credits] =
    jsonBody[Credits]

  object MoviesOutputs {

    import MovieRequests.*

    val jsonRequestedMovieOut: EndpointOutput[RequestedMovie] =
      jsonBody[RequestedMovie]

    val jsonRequestedMoviesListOut: EndpointOutput[List[RequestedMovie]] =
      jsonBody[List[RequestedMovie]]
  }

  object TVShowsOutputs {

    import TvShowRequests.*

    val jsonRequestedTvShowOut: EndpointOutput[RequestedTvShow] =
      jsonBody[RequestedTvShow]

    val jsonRequestedTvShowsListOut: EndpointOutput[List[RequestedTvShow]] =
      jsonBody[List[RequestedTvShow]]
  }

  object SeasonsOutputs {

    import TvSeasonRequests.*

    val jsonRequestedTvSeasonOut: EndpointOutput[RequestedTvSeason] =
      jsonBody[RequestedTvSeason]
  }

  object EpisodesOutputs {

    import TvEpisodeRequests.*

    val jsonRequestedTvEpisodeOut: EndpointOutput[RequestedTvEpisode] =
      jsonBody[RequestedTvEpisode]
  }

}
