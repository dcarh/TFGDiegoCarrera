package unionTypes.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.ids.Media.*

object MediaDecodersForIDs {


  implicit val listMediaUnionDecoder: Decoder[MovieId | TVShowId | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TVShowId | VideogameId | BookId]](
      Decoder[MovieId].widen,
      Decoder[TVShowId].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
  
  implicit val listMediaUnionDecoder2: Decoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]](
      Decoder[MovieId].widen,
      Decoder[TVShowId].widen,
      Decoder[(TVShowId, SeasonNumber)].widen,
      Decoder[(TVShowId, SeasonNumber, EpisodeNumber)].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
  
  implicit val listMediaUnionDecoder3: Decoder[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId]](
      Decoder[TVShowId].widen,
      Decoder[(TVShowId, SeasonNumber)].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
}
