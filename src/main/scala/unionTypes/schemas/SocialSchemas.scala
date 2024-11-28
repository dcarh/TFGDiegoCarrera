package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.social.{MediaContentList, Reply, Review}

import unionTypes.schemas.MediaSchemasForIDs.*
import unionTypes.schemas.SocialSchemasForIDs.*

object SocialSchemas {

  implicit val likeableUnionSchema: Schema[MediaContentList | Review | Reply] = Schema.derivedUnion

}
