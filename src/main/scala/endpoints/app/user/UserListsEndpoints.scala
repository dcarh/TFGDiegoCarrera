package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._

import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId

object UserListsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userListsEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's lists endpoint")
      .description("This endpoint returns all the lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("lists")
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, MediaContentListId), Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's specific list endpoint")
      .description("This endpoint returns a specific list for a user by the ID of the list")
      .get
      .in(PathInputs.pathUsername)
      .in("lists")
      .in(PathInputs.pathListId)
      .out(SocialOutputs.jsonMediaContentListOut)

}
