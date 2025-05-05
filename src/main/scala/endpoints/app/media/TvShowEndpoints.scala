package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.MediaBaseEndpoint.mediaBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*

object TvShowEndpoints {

  val getTvShowBaseEndpoint:
    (String, String) => PublicEndpoint[TvShowId, UserError, Unit, Any] =
    (name, description) => mediaBaseEndpoint(name, description)
      .in("tv_show")
      .in(PathInputs.pathTvShowId)

  val getTvShow: PublicEndpoint[TvShowId, UserError, TvShow, Any] =
    getTvShowBaseEndpoint(
      "getTvShow",
      "This endpoint returns a TV show by its ID",
    )
      .out(MediaOutputs.tvShowOutput)

  val getEntriesForTvShow: PublicEndpoint[TvShowId, UserError, List[Entry], Any] =
    getTvShowBaseEndpoint(
      "getEntriesForTvShow",
      "This endpoint returns a list of all the entries for a TV show",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForTvShow: PublicEndpoint[TvShowId, UserError, List[MediaList], Any] =
    getTvShowBaseEndpoint(
      "getMediaListsForTvShow",
      "This endpoint returns a list of all the media lists for a TV show",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
