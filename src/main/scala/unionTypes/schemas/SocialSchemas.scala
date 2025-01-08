package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.social.{MediaList, Reply, Review}

import unionTypes.schemas.MediaSchemasForIDs.*
import unionTypes.schemas.SocialSchemasForIDs.*

object SocialSchemas {

  implicit val likeableUnionSchema: Schema[MediaList | Review | Reply] = Schema.derivedUnion

}
