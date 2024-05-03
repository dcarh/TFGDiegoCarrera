package api.user

import sttp.tapir._

import modelClasses.social.{MediaContentList, Review, Reply}
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserRepliesEndpoints {

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
      .in(inputs.pathUsername)
      .in("replies")
      .out(outputs.jsonReplyListOut)

  val userRepliesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's replies to lists endpoint")
      .description("This endpoint returns all the replies made by a user specifically to lists")
      .get
      .in(inputs.pathUsername)
      .in("replies" / "lists")
      .out(outputs.jsonListOfElementListOut)

  val userRepliesReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's replies to reviews endpoint")
      .description("This endpoint returns all the replies made by a user specifically to reviews")
      .get
      .in(inputs.pathUsername)
      .in("replies" / "reviews")
      .out(outputs.jsonReviewListOut)

  val userRepliesRepliesListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's replies to replies endpoint")
      .description("This endpoint returns all the replies made by a user specifically to other replies")
      .get
      .in(inputs.pathUsername)
      .in("replies" / "replies")
      .out(outputs.jsonReplyListOut)

}
