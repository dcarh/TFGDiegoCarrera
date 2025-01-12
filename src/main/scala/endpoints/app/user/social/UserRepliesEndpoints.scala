package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Reply
import modelClasses.ids.Social.ReplyId
import modelClasses.ids.User.UserId

object UserRepliesEndpoints {

  private val userRepliesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("replies")

  val getUserReplies: PublicEndpoint[UserId, UserError, List[ReplyId], Any] =
    userRepliesBaseEndpoint(
      "User's replies endpoint",
      "This endpoint returns all the replies made by a user",
      "GET"
    )
      .out(SocialOutputs.listOfRepliesIdsOutput)

//  val userRepliesListsListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
//    userRepliesBaseEndpoint(
//      "User's replies to lists endpoint",
//      "This endpoint returns all the replies made by a user specifically to lists",
//      "GET"
//    )
//      .in("lists")
//      .out(SocialOutputs.listOfRepliesOutput)
//
//  val userRepliesReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
//    userRepliesBaseEndpoint(
//      "User's replies to reviews endpoint",
//      "This endpoint returns all the replies made by a user specifically to reviews",
//      "GET"
//    )
//      .in("reviews")
//      .out(SocialOutputs.listOfRepliesOutput)
//
//  val userRepliesRepliesListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
//    userRepliesBaseEndpoint(
//      "User's replies to replies endpoint",
//      "This endpoint returns all the replies made by a user specifically to other replies",
//      "GET"
//    )
//      .in("replies")
//      .out(SocialOutputs.listOfRepliesOutput)

}
