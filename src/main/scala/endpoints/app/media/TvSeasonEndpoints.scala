package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.TvShowEndpoints.getTvShowBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, MediaListId}

object TvSeasonEndpoints {

  val getTvSeasonBaseEndpoint:
    (String, String, String) => PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, Unit, Any] =
    (name, description, method) => getTvShowBaseEndpoint(name, description, method)
      .in("season")
      .in(PathInputs.pathTvSeasonNumber)

  val getTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, TvSeason, Any] =
    getTvSeasonBaseEndpoint(
      "getSeason",
      "This endpoint returns the season specified by the ID introduced",
      "GET"
    )
      .out(MediaOutputs.seasonOutput)

  val getEntriesForTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber, List[EntryId]), UserError, List[Entry], Any] =
    getTvSeasonBaseEndpoint(
      "getEntriesForTvSeason",
      "This endpoint returns a list of all the entries for a specific TV season",
      "POST_IGDB"
    )
      .in("entries")
      .in(JsonInputs.jsonListOfEntriesIds)
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber, List[MediaListId]), UserError, List[MediaList], Any] =
    getTvSeasonBaseEndpoint(
      "getMediaListsForTvSeason",
      "This endpoint returns a list of all the media lists for a specific TV season",
      "POST_IGDB"
    )
      .in("media_lists")
      .in(JsonInputs.jsonListOfMediaListsIds)
      .out(SocialOutputs.listOfMediaListsOutput)

}
