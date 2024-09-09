package utility

import upickle.default.*
import modelClasses.app.user.User
import modelClasses.app.social.*
import modelClasses.app.media.*
import modelClasses.app.chatting.*

object JsonReaders {

  val chatsFromJson = read[List[Chat]](os.read(os.pwd / "Chats.json"))
  val entriesFromJson = read[List[Entry]](os.read(os.pwd / "Entries.json"))
  val likesFromJson = read[List[Like]](os.read(os.pwd / "Likes.json"))
  val mediaContentListsFromJson = read[List[MediaContentList]](os.read(os.pwd / "MediaContentLists.json"))
  val messagesFromJson = read[List[Message]](os.read(os.pwd / "Messages.json"))
  val ratingsFromJson = read[List[Rating]](os.read(os.pwd / "Ratings.json"))
  val repliesFromJson = read[List[Reply]](os.read(os.pwd / "Replies.json"))
  val reviewsFromJson = read[List[Review]](os.read(os.pwd / "Reviews.json"))
  val usersFromJson = read[List[User]](os.read(os.pwd / "Users.json"))
  
}
