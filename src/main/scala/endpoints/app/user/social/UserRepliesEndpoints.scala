package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Reply
import modelClasses.ids.User.UserId

object UserRepliesEndpoints {

  val userRepliesListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
    userBaseEndpoint(
      "User's replies endpoint",
      "This endpoint returns all the replies made by a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("replies")
      .out(SocialOutputs.listOfRepliesSuccess)

  val userRepliesListsListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
    userBaseEndpoint(
      "User's replies to lists endpoint",
      "This endpoint returns all the replies made by a user specifically to lists",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("replies" / "lists")
      .out(SocialOutputs.listOfRepliesSuccess)

  val userRepliesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
    userBaseEndpoint(
      "User's replies to reviews endpoint",
      "This endpoint returns all the replies made by a user specifically to reviews",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("replies" / "reviews")
      .out(SocialOutputs.listOfRepliesSuccess)

  val userRepliesRepliesListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
    userBaseEndpoint(
      "User's replies to replies endpoint",
      "This endpoint returns all the replies made by a user specifically to other replies",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("replies" / "replies")
      .out(SocialOutputs.listOfRepliesSuccess)

}
