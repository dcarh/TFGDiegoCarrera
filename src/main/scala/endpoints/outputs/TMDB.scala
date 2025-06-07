package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import domain.tmdb.*
import domain.tmdb.Common.{Credits, Results, Result}
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import codecs.decoders.MediaIDs.*
import codecs.encoders.MediaIDs.*

object TMDB {
  
  val jsonResultsOut: EndpointOutput[Results] =
    jsonBody[Results]
  
  val jsonListOfResultOut: EndpointOutput[List[Result]] =
    jsonBody[List[Result]]
    
  val jsonCreditsOut: EndpointOutput[Credits] =
    jsonBody[Credits]

  object MoviesOutputs {

    import MovieRequests.*

    val jsonRequestedMovieOut: EndpointOutput[MovieFromTMDB] =
      jsonBody[MovieFromTMDB]

    val jsonRequestedMoviesListOut: EndpointOutput[List[MovieFromTMDB]] =
      jsonBody[List[MovieFromTMDB]]
  }

  object TVShowsOutputs {

    import TvShowRequests.*

    val jsonRequestedTvShowOut: EndpointOutput[TvShowFromTMDB] =
      jsonBody[TvShowFromTMDB]

    val jsonRequestedTvShowsListOut: EndpointOutput[List[TvShowFromTMDB]] =
      jsonBody[List[TvShowFromTMDB]]
  }

  object SeasonsOutputs {

    import TvSeasonRequests.*

    val jsonRequestedTvSeasonOut: EndpointOutput[TvSeasonFromTMDB] =
      jsonBody[TvSeasonFromTMDB]
  }

  object EpisodesOutputs {

    import TvEpisodeRequests.*

    val jsonRequestedTvEpisodeOut: EndpointOutput[TvEpisodeFromTMDB] =
      jsonBody[TvEpisodeFromTMDB]
  }

}
