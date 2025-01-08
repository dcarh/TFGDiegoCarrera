package unionTypes.schemas

import modelClasses.ids.Social.{MediaListId, ReplyId, ReviewId}

import sttp.tapir.*
import sttp.tapir.generic.auto.*

object SocialSchemasForIDs {

  implicit val socialIdsSchema: Schema[MediaListId | ReviewId | ReplyId] = Schema.derivedUnion
  
//  implicit val mediaUnionSchema5: Schema[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Schema.derivedUnion
}
