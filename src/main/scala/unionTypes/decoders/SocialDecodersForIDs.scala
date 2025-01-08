package unionTypes.decoders

import cats.syntax.functor.*

import io.circe.Decoder
import io.circe.generic.auto.*

import modelClasses.ids.Social.{MediaContentListId, ReplyId, ReviewId}

object SocialDecodersForIDs {
  
  implicit val mediaUnionDecoder4: Decoder[MediaContentListId | ReviewId | ReplyId] = Decoder.instance { cursor =>
    List[Decoder[MediaContentListId | ReviewId | ReplyId]](
      Decoder[MediaContentListId].widen,
      Decoder[ReviewId].widen,
      Decoder[ReplyId].widen,
    ).reduceLeft(_ or _).apply(cursor)
  }

  //  implicit val mediaUnionDecoder5: Decoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Decoder.instance { cursor =>
  //    List[Decoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId]](
  //      Decoder[MovieId].widen,
  //      Decoder[TvShowId].widen,
  //      Decoder[(TvShowId, SeasonNumber)].widen,
  //      Decoder[(TvShowId, SeasonNumber, EpisodeNumber)].widen,
  //      Decoder[VideogameId].widen,
  //      Decoder[BookId].widen,
  //      Decoder[MediaContentListId].widen,
  //      Decoder[ReviewId].widen,
  //      Decoder[ReplyId].widen,
  //    ).reduceLeft(_ or _).apply(cursor)
  //  }
}
