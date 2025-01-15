package unionTypes.schemas

import modelClasses.ids.Social.{MediaListId, ReplyId, ReviewId}

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import sttp.tapir.Schema
import sttp.tapir.SchemaType.*

object SocialSchemasForIDs {

  private val extractDiscriminator: (MediaListId | ReviewId | ReplyId) => String =
    {
      case _: MediaListId => "MediaListId"
      case _: ReviewId => "ReviewId"
      case _: ReplyId => "ReplyId"
    }

  implicit val socialIdsSchema: Schema[MediaListId | ReviewId | ReplyId] =
    Schema.oneOfUsingField[MediaListId | ReviewId | ReplyId, String](
      extractDiscriminator,
      identity
    )(
      "MediaListId" -> Schema.derived[MediaListId],
      "ReviewId"    -> Schema.derived[ReviewId],
      "ReplyId"     -> Schema.derived[ReplyId]
    )
}
