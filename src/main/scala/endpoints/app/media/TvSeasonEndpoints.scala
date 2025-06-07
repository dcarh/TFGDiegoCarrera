package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.TvShowEndpoints.getTvShowBaseEndpoint
import endpoints.io.inputs.Common.*
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.ids.Media.*

object TvSeasonEndpoints {

  val getTvSeasonBaseEndpoint:
    (String, String) => PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, Unit, Any] =
    (name, description) => getTvShowBaseEndpoint(name, description)
      .in("season")
      .in(PathInputs.pathTvSeasonNumber)

  val getTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, TvSeason, Any] =
    getTvSeasonBaseEndpoint(
      "getTvSeason",
      "This endpoint returns a TV season by its ID",
    )
      .out(MediaOutputs.seasonOutput)

  val getEntriesForTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, List[Entry], Any] =
    getTvSeasonBaseEndpoint(
      "getEntriesForTvSeason",
      "This endpoint returns a list of all the entries for a TV season",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, List[MediaList], Any] =
    getTvSeasonBaseEndpoint(
      "getMediaListsForTvSeason",
      "This endpoint returns a list of all the media lists for a TV season",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
