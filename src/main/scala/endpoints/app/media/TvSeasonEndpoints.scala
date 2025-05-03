package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.TvShowEndpoints.getTvShowBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*

object TvSeasonEndpoints {

  val getTvSeasonBaseEndpoint:
    (String, String) => PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, Unit, Any] =
    (name, description) => getTvShowBaseEndpoint(name, description)
      .in("season")
      .in(PathInputs.pathTvSeasonNumber)

  val getTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, TvSeason, Any] =
    getTvSeasonBaseEndpoint(
      "getTvSeason",
      "This endpoint returns the season specified by the ID introduced",
    )
      .out(MediaOutputs.seasonOutput)

  val getEntriesForTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, List[Entry], Any] =
    getTvSeasonBaseEndpoint(
      "getEntriesForTvSeason",
      "This endpoint returns a list of all the entries for a specific TV season",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, List[MediaList], Any] =
    getTvSeasonBaseEndpoint(
      "getMediaListsForTvSeason",
      "This endpoint returns a list of all the media lists for a specific TV season",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
