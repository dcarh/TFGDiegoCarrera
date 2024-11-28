package unionTypes.schemas

import modelClasses.ids.Social.{MediaContentListId, ReplyId, ReviewId}

import sttp.tapir.*
import sttp.tapir.generic.auto.*

object SocialSchemasForIDs {

  implicit val mediaUnionSchema4: Schema[MediaContentListId | ReviewId | ReplyId] = Schema.derivedUnion
  
//  implicit val mediaUnionSchema5: Schema[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Schema.derivedUnion
}
