package endpoints.app.chatting

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.app.chatting.Chat
import domain.ids.Chatting.ChatId
import domain.app.chatting.Message
import domain.ids.Chatting.MessageId
import domain.ids.User.UserId

object ChattingEndpoints {

  private val chatsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("chats")
    
  private val chatBaseEndpoint:
    (String, String, String) => PublicEndpoint[(UserId, ChatId), UserError, Unit, Any] =
    (name, description, method) => chatsBaseEndpoint(name, description, method)
      .in(PathInputs.pathChatId)

  private val messagesBaseEndpoint:
    (String, String, String) => PublicEndpoint[(UserId, ChatId), UserError, Unit, Any] =
    (name, description, method) => chatBaseEndpoint(name, description, method)
      .in("messages")

  private val messageBaseEndpoint:
    (String, String, String) => PublicEndpoint[(UserId, ChatId, MessageId), UserError, Unit, Any] =
    (name, description, method) => messagesBaseEndpoint(name, description, method)
      .in(PathInputs.pathMessageId)

  val getChats: PublicEndpoint[(UserId, Option[Boolean]), UserError, List[ChatId], Any] =
    chatsBaseEndpoint(
      "getChats",
      "This endpoint returns a list of the user's chat IDs",
      "GET"
    )
      .in(QueryInputs.queryArchived)
      .out(ChattingOutputs.listOfChatIdsOutput)

  val getChat: PublicEndpoint[(UserId, ChatId), UserError, Chat, Any] =
    chatBaseEndpoint(
      "getChat",
      "This endpoint returns a specific chat by its ID",
      "GET"
    )
      .out(ChattingOutputs.chatOutput)

  val archiveChat: PublicEndpoint[(UserId, ChatId), UserError, Chat, Any] =
    chatBaseEndpoint(
      "archiveChat",
      "This endpoint archives a specific chat by its ID",
      "PUT"
    )
      .in("archive")
      .out(ChattingOutputs.chatOutput)

  val deleteChat: PublicEndpoint[(UserId, ChatId), UserError, Unit, Any] =
    chatBaseEndpoint(
      "deleteChat",
      "This endpoint deletes a specific chat by its ID",
      "DELETE"
    )
      .in("delete")

  val getChatMessages: PublicEndpoint[(UserId, ChatId), UserError, List[MessageId], Any] =
    messagesBaseEndpoint(
      "getChatMessages",
      "This endpoint returns a list of the messages IDs of a user's chat",
      "GET"
    )
      .out(ChattingOutputs.listOfMessagesIdsOutput)

  val getMessage: PublicEndpoint[(UserId, ChatId, MessageId), UserError, Message, Any] =
    messageBaseEndpoint(
      "getMessage",
      "This endpoint returns a specific message of a user's chat by its ID",
      "GET"
    )
      .out(ChattingOutputs.messageOutput)

  val sendMessage: PublicEndpoint[(UserId, ChatId, UserId, Message), UserError, Message, Any] =
    messagesBaseEndpoint(
      "sendMessage",
      "This endpoint sends a specific message from one user to another user and returns it",
      "POST"
    )
      .in("send")
      .in(PathInputs.pathUserId)
      .in(JsonInputs.jsonMessage)
      .out(ChattingOutputs.messageOutput)

  val deleteMessage: PublicEndpoint[(UserId, ChatId, MessageId), UserError, Unit, Any] =
    messageBaseEndpoint(
      "deleteMessage",
      "This endpoint deletes a specific message of a user's chat by its Id",
      "DELETE"
    )
      .in("delete")

}
