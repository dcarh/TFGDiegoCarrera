package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.social.Reply

object UserRepliesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userRepliesListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies endpoint")
      .description("This endpoint returns all the replies made by a user")
      .get
      .in(PathInputs.pathUsername)
      .in("replies")
      .out(SocialOutputs.jsonReplyListOut)

  val userRepliesListsListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to lists endpoint")
      .description("This endpoint returns all the replies made by a user specifically to lists")
      .get
      .in(PathInputs.pathUsername)
      .in("replies" / "lists")
      .out(SocialOutputs.jsonReplyListOut)

  val userRepliesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to reviews endpoint")
      .description("This endpoint returns all the replies made by a user specifically to reviews")
      .get
      .in(PathInputs.pathUsername)
      .in("replies" / "reviews")
      .out(SocialOutputs.jsonReplyListOut)

  val userRepliesRepliesListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to replies endpoint")
      .description("This endpoint returns all the replies made by a user specifically to other replies")
      .get
      .in(PathInputs.pathUsername)
      .in("replies" / "replies")
      .out(SocialOutputs.jsonReplyListOut)

}
