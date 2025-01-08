package unionTypes.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.ids.Media.*

object MediaDecodersForIDs {


  implicit val listMediaUnionDecoder: Decoder[MovieId | TvShowId | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TvShowId | VideogameId | BookId]](
      Decoder[MovieId].widen,
      Decoder[TvShowId].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
  
  implicit val listMediaUnionDecoder2: Decoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]](
      Decoder[MovieId].widen,
      Decoder[TvShowId].widen,
      Decoder[(TvShowId, SeasonNumber)].widen,
      Decoder[(TvShowId, SeasonNumber, EpisodeNumber)].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
  
  implicit val listMediaUnionDecoder3: Decoder[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    List[Decoder[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]](
      Decoder[TvShowId].widen,
      Decoder[(TvShowId, SeasonNumber)].widen,
      Decoder[VideogameId].widen,
      Decoder[BookId].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
}
