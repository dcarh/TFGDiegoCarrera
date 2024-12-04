package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.social.Reply
import modelClasses.ids.Social.ReplyId

object RepliesEndpoints {

  private val replyBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "reply", method)

  val specificReplyEndpoint: PublicEndpoint[ReplyId, ErrorInfo, Reply, Any] =
    replyBaseEndpoint(
      "Specific reply endpoint",
      "This endpoint returns a specific reply by its Id",
      "GET"
    )
      .in(PathInputs.pathReplyId)
      .out(SocialOutputs.replySuccess)

}
