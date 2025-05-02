package codecs.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.social.{MediaList, Reply, Review}

import MediaIDsUnionTypes.*
import SocialIDsUnionTypes.*

object SocialUnionTypes {

  implicit val likeableUnionSchema: Schema[MediaList | Review | Reply] = Schema.derivedUnion

}
