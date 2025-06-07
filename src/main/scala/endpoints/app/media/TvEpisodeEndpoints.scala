package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.TvSeasonEndpoints.getTvSeasonBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.ids.Media.*

object TvEpisodeEndpoints {

  private val getTvEpisodeBaseEndpoint:
    (String, String) => PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Unit, Any] =
    (name, description) => getTvSeasonBaseEndpoint(name, description)
      .in("episode")
      .in(PathInputs.pathTvEpisodeNumber)

  val getTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, TvEpisode, Any] =
    getTvEpisodeBaseEndpoint(
      "getTvEpisode",
      "This endpoint returns a TV episode by its ID",
    )
      .out(MediaOutputs.episodeOutput)

  val getEntriesForTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, List[Entry], Any] =
    getTvEpisodeBaseEndpoint(
      "getEntriesForTvEpisode",
      "This endpoint returns a list of all the entries for a TV episode",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, List[MediaList], Any] =
    getTvEpisodeBaseEndpoint(
      "getMediaListsForTvEpisode",
      "This endpoint returns a list of all the media lists for a TV episode",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
