package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.TvSeasonEndpoints.getTvSeasonBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, MediaListId}

object TvEpisodeEndpoints {

  private val getTvEpisodeBaseEndpoint:
    (String, String, String) => PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Unit, Any] =
    (name, description, method) => getTvSeasonBaseEndpoint(name, description, method)
      .in("episode")
      .in(PathInputs.pathTvEpisodeNumber)

  val getTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, TvEpisode, Any] =
    getTvEpisodeBaseEndpoint(
      "getEpisode",
      "This endpoint returns the episode specified by the ID introduced",
      "GET"
    )
      .out(MediaOutputs.episodeOutput)

  val getEntriesForTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber, List[EntryId]), UserError, List[Entry], Any] =
    getTvEpisodeBaseEndpoint(
      "getEntriesForTvEpisode",
      "This endpoint returns a list of all the entries for a specific TV episode",
      "POST_IGDB"
    )
      .in("entries")
      .in(JsonInputs.jsonListOfEntriesIds)
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber, List[MediaListId]), UserError, List[MediaList], Any] =
    getTvEpisodeBaseEndpoint(
      "getMediaListsForTvEpisode",
      "This endpoint returns a list of all the media lists for a specific TV episode",
      "POST_IGDB"
    )
      .in("mediaLists")
      .in(JsonInputs.jsonListOfMediaListsIds)
      .out(SocialOutputs.listOfMediaListsOutput)

}
