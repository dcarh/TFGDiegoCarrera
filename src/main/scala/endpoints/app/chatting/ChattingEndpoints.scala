package endpoints.app.chatting

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId
import modelClasses.app.chatting.Message
import modelClasses.ids.Chatting.MessageId
import modelClasses.ids.User.UserId

object ChattingEndpoints {

//  private val chatBaseEndpoint:
//    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
//    (name, description, method) => httpMethodEndpoint(name, description, "chat", method)

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

//  getChats -> app / user / {user_id} / chats
//  getChat -> app / user / {user_id} / chats / {chat_id}
//  deleteChat -> app / user / {user_id} / chats / {chat_id} / delete
//  archiveChat -> app / user / {user_id} / chats / {chat_id} / archive
//  getChatMessages -> app / user / {user_id} / chats / {chat_id} / messages
//  getMessage -> app / user / {user_id} / chats / {chat_id} / messages / {message_id}
//  sendMessage -> app / user / {user_id} / chats / {chat_id} / messages / send / {user_id}
//  deleteMessage -> app / user / {user_id} / chats / {chat_id} / messages / {message_id} /delete

  val getChats: PublicEndpoint[(UserId, Option[String], Option[Boolean]), UserError, List[ChatId], Any] =
    chatsBaseEndpoint(
      "Get chats endpoint",
      "This endpoint returns a list containing the IDs of the chats of the user",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .in(QueryInputs.queryArchived)
      .out(ChattingOutputs.listOfChatIdsOutput)

  val getChat: PublicEndpoint[(UserId, ChatId), UserError, Chat, Any] =
    chatBaseEndpoint(
      "Get chat endpoint",
      "This endpoint returns a specific chat by its ID",
      "GET"
    )
      .out(ChattingOutputs.chatOutput)

  val archiveChat: PublicEndpoint[(UserId, ChatId), UserError, Chat, Any] =
    chatBaseEndpoint(
      "Archive chat endpoint",
      "This endpoint archives a specific chat by its ID",
      "PUT"
    )
      .in("archive")
      .out(ChattingOutputs.chatOutput)

  val deleteChat: PublicEndpoint[(UserId, ChatId), UserError, Unit, Any] =
    chatBaseEndpoint(
      "Delete chat endpoint",
      "This endpoint deletes a specific chat by its ID",
      "DELETE"
    )
      .in("delete")

  val getChatMessages: PublicEndpoint[(UserId, ChatId), UserError, List[MessageId], Any] =
    messagesBaseEndpoint(
      "Get chats endpoint",
      "This endpoint returns a list containing the IDs of a chat of the user",
      "GET"
    )
      .out(ChattingOutputs.listOfMessagesIdsOutput)

  val getMessage: PublicEndpoint[(UserId, ChatId, MessageId), UserError, Message, Any] =
    messageBaseEndpoint(
      "Get message endpoint",
      "This endpoint returns a specific message by its ID",
      "GET"
    )
      .out(ChattingOutputs.messageOutput)

  val sendMessage: PublicEndpoint[(UserId, ChatId, UserId, Message), UserError, Message, Any] =
    messagesBaseEndpoint(
      "Send message endpoint",
      "This endpoint sends a specific message from one user to another user and returns it",
      "POST"
    )
      .in("send")
      .in(PathInputs.pathUserId)
      .in(JsonInputs.jsonMessage)
      .out(ChattingOutputs.messageOutput)

  val deleteMessage: PublicEndpoint[(UserId, ChatId, MessageId), UserError, Unit, Any] =
    messageBaseEndpoint(
      "Delete message endpoint",
      "This endpoint deletes a specific message by its Id",
      "DELETE"
    )
      .in("delete")

}
