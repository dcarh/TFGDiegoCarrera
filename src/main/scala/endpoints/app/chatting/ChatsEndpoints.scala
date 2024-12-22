package endpoints.app.chatting

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId

object ChatsEndpoints {

  private val chatBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "chat", method)

  val getChatEndpoint: PublicEndpoint[ChatId, UserError, Chat, Any] =
    chatBaseEndpoint(
      "Get chat endpoint", 
      "This endpoint returns a specific chat by its Id",
      "GET"
    )
      .in(PathInputs.pathChatId)
      .out(ChattingOutputs.chatSuccess)

  val createChatEndpoint: PublicEndpoint[Chat, UserError, Chat, Any] =
    chatBaseEndpoint(
      "Create chat endpoint", 
      "This endpoint creates a new chat and returns it",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonChat)
      .out(ChattingOutputs.chatSuccess)

  val editChatEndpoint: PublicEndpoint[(ChatId, Chat), UserError, Chat, Any] =
    chatBaseEndpoint(
      "Edit chat endpoint", 
      "This endpoint edits a specific chat by its Id and returns it",
      "PUT"
    )
      .in(PathInputs.pathChatId)
      .in("edit")
      .in(JsonInputs.jsonChat)
      .out(ChattingOutputs.chatSuccess)

  val deleteChatEndpoint: PublicEndpoint[ChatId, UserError, Unit, Any] =
    chatBaseEndpoint(
      "Delete chat endpoint", 
      "This endpoint deletes a specific chat by its Id",
      "DELETE"
    )
      .in("delete")
      .in(PathInputs.pathChatId)

}
