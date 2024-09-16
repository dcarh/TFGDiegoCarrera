package schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.ids.Media.*
import modelClasses.ids.Social.*

object UnionTypesForIds {


  implicit val listMediaUnionEncoder: Encoder[MovieId | TVShowId | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TVShowId => tvShowId.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }

  implicit val listMediaUnionDecoder: Decoder[MovieId | TVShowId | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TVShowId | VideogameId | BookId]](
      Decoder[MovieId].widen,
      Decoder[TVShowId].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val listMediaUnionSchema: Schema[MovieId | TVShowId  | VideogameId | BookId] = Schema.derivedUnion

  implicit val mediaUnionEncoder3: Encoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TVShowId => tvShowId.asJson
    case seasonNumber: (TVShowId, SeasonNumber) => seasonNumber.asJson
    case episodeNumber: (TVShowId, SeasonNumber, EpisodeNumber) => episodeNumber.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }

  implicit val mediaUnionDecoder3: Decoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]](
      Decoder[MovieId].widen,
      Decoder[TVShowId].widen,
      Decoder[(TVShowId, SeasonNumber)].widen,
      Decoder[(TVShowId, SeasonNumber, EpisodeNumber)].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val mediaUnionSchema3: Schema[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Schema.derivedUnion


  implicit val mediaUnionEncoder4: Encoder[MovieId | TVShowId | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TVShowId => tvShowId.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
    case mediaContentListId: MediaContentListId => mediaContentListId.asJson
    case reviewId: ReviewId => reviewId.asJson
    case replyId: ReplyId => replyId.asJson
  }

  implicit val mediaUnionDecoder4: Decoder[MovieId | TVShowId | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TVShowId | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId]](
      Decoder[MovieId].widen,
      Decoder[TVShowId].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen,
      Decoder[MediaContentListId].widen,
      Decoder[ReviewId].widen,
      Decoder[ReplyId].widen,
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val mediaUnionSchema4: Schema[MovieId | TVShowId | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Schema.derivedUnion

}
