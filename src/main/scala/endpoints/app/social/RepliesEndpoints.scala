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

  val getReplyEndpoint: PublicEndpoint[ReplyId, UserError, Reply, Any] =
    replyBaseEndpoint(
      "Get reply endpoint",
      "This endpoint returns a specific reply by its Id",
      "GET"
    )
      .in(PathInputs.pathReplyId)
      .out(SocialOutputs.replySuccess)

  val createReplyEndpoint: PublicEndpoint[Unit, UserError, Reply, Any] =
    replyBaseEndpoint(
      "Create reply endpoint",
      "This endpoint creates a reply and returns it in case of success",
      "POST"
    )
      .in("create")
      .out(SocialOutputs.replySuccess)

  val editReplyEndpoint: PublicEndpoint[ReplyId, UserError, Reply, Any] =
    replyBaseEndpoint(
      "Edit reply endpoint",
      "This endpoint allows to edit a reply and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathReplyId)
      .in("edit")
      .out(SocialOutputs.replySuccess)

  val deleteReplyEndpoint: PublicEndpoint[ReplyId, UserError, Unit, Any] =
    replyBaseEndpoint(
      "Delete reply endpoint",
      "This endpoint deletes a reply returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathReplyId)
      .in("delete")

}
