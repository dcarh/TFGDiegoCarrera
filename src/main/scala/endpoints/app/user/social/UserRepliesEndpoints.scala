package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Reply
import modelClasses.ids.User.UserId

object UserRepliesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userRepliesListEndpoint: PublicEndpoint[UserId, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies endpoint")
      .description("This endpoint returns all the replies made by a user")
      .get
      .in(PathInputs.pathUserId)
      .in("replies")
      .out(SocialOutputs.jsonReplyListOut)

  val userRepliesListsListEndpoint: PublicEndpoint[UserId, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to lists endpoint")
      .description("This endpoint returns all the replies made by a user specifically to lists")
      .get
      .in(PathInputs.pathUserId)
      .in("replies" / "lists")
      .out(SocialOutputs.jsonReplyListOut)

  val userRepliesReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to reviews endpoint")
      .description("This endpoint returns all the replies made by a user specifically to reviews")
      .get
      .in(PathInputs.pathUserId)
      .in("replies" / "reviews")
      .out(SocialOutputs.jsonReplyListOut)

  val userRepliesRepliesListEndpoint: PublicEndpoint[UserId, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to replies endpoint")
      .description("This endpoint returns all the replies made by a user specifically to other replies")
      .get
      .in(PathInputs.pathUserId)
      .in("replies" / "replies")
      .out(SocialOutputs.jsonReplyListOut)

}
