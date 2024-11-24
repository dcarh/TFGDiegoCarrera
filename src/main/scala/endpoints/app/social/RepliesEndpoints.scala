package endpoints.app.social

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Reply
import modelClasses.ids.Social.ReplyId

object RepliesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  private val repliesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "replies")

  private val replyBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "reply")
  
  val repliesEndpoint: PublicEndpoint[Option[String], Unit, List[Reply], Any] =
    repliesBaseEndpoint
      .name("Replies endpoint")
      .description("This endpoint returns a list with all the replies in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonReplyListOut)

  val specificReplyEndpoint: PublicEndpoint[ReplyId, Unit, Reply, Any] =
    replyBaseEndpoint
      .name("Specific reply endpoint")
      .description("This endpoint returns a specific reply by its Id")
      .get
      .in(PathInputs.pathReplyId)
      .out(SocialOutputs.jsonReplyOut)

}
