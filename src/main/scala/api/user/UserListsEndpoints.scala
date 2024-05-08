package api.user

import sttp.tapir._

import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserListsEndpoints {

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
      .in(inputs.pathUsername)
      .in("lists")
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, MediaContentList.Id), Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's specific list endpoint")
      .description("This endpoint returns a specific list for a user by the ID of the list")
      .get
      .in(inputs.pathUsername)
      .in("lists")
      .in(inputs.pathListId)
      .out(outputs.jsonMediaContentListOut)

}
