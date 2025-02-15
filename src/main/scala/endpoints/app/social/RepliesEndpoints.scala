package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Reply
import modelClasses.ids.Social.ReplyId

object RepliesEndpoints {

  private val replyBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "reply", method)

  val getReply: PublicEndpoint[ReplyId, UserError, Reply, Any] =
    replyBaseEndpoint(
      "getReply",
      "This endpoint returns a specific reply by its Id",
      "GET"
    )
      .in(PathInputs.pathReplyId)
      .out(SocialOutputs.replyOutput)

  val createReply: PublicEndpoint[Reply, UserError, Reply, Any] =
    replyBaseEndpoint(
      "createReply",
      "This endpoint creates a reply and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonReply)
      .out(SocialOutputs.replyOutput)

  val editReply: PublicEndpoint[(ReplyId, Reply), UserError, Reply, Any] =
    replyBaseEndpoint(
      "editReply",
      "This endpoint allows to edit a reply and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathReplyId)
      .in("edit")
      .in(JsonInputs.jsonReply)
      .out(SocialOutputs.replyOutput)

  val deleteReply: PublicEndpoint[ReplyId, UserError, Unit, Any] =
    replyBaseEndpoint(
      "deleteReply",
      "This endpoint deletes a reply returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathReplyId)
      .in("delete")

}
