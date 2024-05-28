package api.common

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.Encoder
import io.circe.Decoder
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe._
import cats.syntax.functor._


import modelClasses.media._
import modelClasses.social._
import modelClasses.user._
import modelClasses.ErrorInfo
import modelClasses.chatting.Chat

class Outputs {

  //implicit val movieEncoder: Encoder[Movie] = deriveEncoder[Movie]
  //implicit val tvShowEncoder: Encoder[TVShow] = deriveEncoder[TVShow]
  //implicit val seasonEncoder: Encoder[Season] = deriveEncoder[Season]
  //implicit val episodeEncoder: Encoder[Episode] = deriveEncoder[Episode]
  //implicit val videogameEncoder: Encoder[Videogame] = deriveEncoder[Videogame]
  //implicit val bookEncoder: Encoder[Book] = deriveEncoder[Book]
//
  //implicit val movieDecoder: Decoder[Movie] = deriveDecoder[Movie]
  //implicit val tvShowDecoder: Decoder[TVShow] = deriveDecoder[TVShow]
  //implicit val seasonDecoder: Decoder[Season] = deriveDecoder[Season]
  //implicit val episodeDecoder: Decoder[Episode] = deriveDecoder[Episode]
  //implicit val videogameDecoder: Decoder[Videogame] = deriveDecoder[Videogame]
  //implicit val bookDecoder: Decoder[Book] = deriveDecoder[Book]

  implicit val mediaEncoder: Encoder[Movie | TVShow | Season | Episode | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TVShow => tvShow.asJson
    case season: Season => season.asJson
    case episode: Episode => episode.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }

  implicit val mediaDecoder: Decoder[Movie | TVShow | Season | Episode | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TVShow | Season | Episode | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TVShow].widen,
      Decoder[Season].widen,
      Decoder[Episode].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val s: Schema[Movie | TVShow | Season | Episode | Videogame | Book] = Schema.derivedUnion

  val jsonMovieOut: EndpointOutput[Movie] =
    jsonBody[Movie]

  val jsonTVShowOut: EndpointOutput[TVShow] =
    jsonBody[TVShow]

  val jsonSeasonOut: EndpointOutput[Season] =
    jsonBody[Season]

  val jsonEpisodeOut: EndpointOutput[Episode] =
    jsonBody[Episode]

  val jsonVideogameOut: EndpointOutput[Videogame] =
    jsonBody[Videogame]

  val jsonBookOut: EndpointOutput[Book] =
    jsonBody[Book]

  val jsonEntryOut: EndpointOutput[Entry] =
    jsonBody[Entry]

  val jsonReviewOut: EndpointOutput[Review] =
    jsonBody[Review]

  val jsonReplyOut: EndpointOutput[Reply] =
    jsonBody[Reply]

  val jsonUserOut: EndpointOutput[User] =
    jsonBody[User]

  val jsonChatOut: EndpointOutput[Chat] =
    jsonBody[Chat]

  val jsonMediaContentListOut: EndpointOutput[MediaContentList] =
    jsonBody[MediaContentList]

  val jsonMediaListOut: EndpointOutput[List[Movie | TVShow | Season | Episode | Videogame | Book]] =
    jsonBody[List[Movie | TVShow | Season | Episode | Videogame | Book]]

  val jsonMovieListOut: EndpointOutput[List[Movie]] =
    jsonBody[List[Movie]]

  val jsonTVShowListOut: EndpointOutput[List[TVShow]] =
    jsonBody[List[TVShow]]

  val jsonSeasonListOut: EndpointOutput[List[Season]] =
    jsonBody[List[Season]]

  val jsonEpisodeListOut: EndpointOutput[List[Episode]] =
    jsonBody[List[Episode]]

  val jsonVideogameListOut: EndpointOutput[List[Videogame]] =
    jsonBody[List[Videogame]]

  val jsonBookListOut: EndpointOutput[List[Book]] =
    jsonBody[List[Book]]

  val jsonEntryListOut: EndpointOutput[List[Entry]] =
    jsonBody[List[Entry]]

  val jsonReviewListOut: EndpointOutput[List[Review]] =
    jsonBody[List[Review]]

  val jsonReplyListOut: EndpointOutput[List[Reply]] =
    jsonBody[List[Reply]]

  val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  val jsonChatListOut: EndpointOutput[List[Chat]] =
    jsonBody[List[Chat]]

  val jsonListOfMediaContentListOut: EndpointOutput[List[MediaContentList]] =
    jsonBody[List[MediaContentList]]

  val jsonSettingsOut: EndpointOutput[UserSettings] =
    jsonBody[UserSettings]

  val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
    jsonBody[ErrorInfo]
}

object Outputs {
  val outputs = new Outputs()
}
