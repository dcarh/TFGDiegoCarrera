package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.TvSeasonEndpoints.getTvSeasonBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*

object TvEpisodeEndpoints {

  private val getTvEpisodeBaseEndpoint:
    (String, String) => PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Unit, Any] =
    (name, description) => getTvSeasonBaseEndpoint(name, description)
      .in("episode")
      .in(PathInputs.pathTvEpisodeNumber)

  val getTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, TvEpisode, Any] =
    getTvEpisodeBaseEndpoint(
      "getTvEpisode",
      "This endpoint returns the episode specified by the ID introduced",
    )
      .out(MediaOutputs.episodeOutput)

  val getEntriesForTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, List[Entry], Any] =
    getTvEpisodeBaseEndpoint(
      "getEntriesForTvEpisode",
      "This endpoint returns a list of all the entries for a specific TV episode",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, List[MediaList], Any] =
    getTvEpisodeBaseEndpoint(
      "getMediaListsForTvEpisode",
      "This endpoint returns a list of all the media lists for a specific TV episode",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
