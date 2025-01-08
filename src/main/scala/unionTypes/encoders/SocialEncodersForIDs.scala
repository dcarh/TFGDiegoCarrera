package unionTypes.encoders

import io.circe.Encoder
import io.circe.generic.auto.*
import io.circe.syntax.*

import modelClasses.ids.Social.{MediaContentListId, ReplyId, ReviewId}

object SocialEncodersForIDs {


  implicit val mediaUnionEncoder4: Encoder[MediaContentListId | ReviewId | ReplyId] = Encoder.instance {
    case mediaContentListId: MediaContentListId => mediaContentListId.asJson
    case reviewId: ReviewId => reviewId.asJson
    case replyId: ReplyId => replyId.asJson
  }

  //  implicit val mediaUnionEncoder5: Encoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Encoder.instance {
  //    case movieId: MovieId => movieId.asJson
  //    case tvShowId: TvShowId => tvShowId.asJson
  //    case seasonNumber: (TvShowId, SeasonNumber) => seasonNumber.asJson
  //    case episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber) => episodeNumber.asJson
  //    case videogameId: VideogameId => videogameId.asJson
  //    case bookId: BookId => bookId.asJson
  //    case mediaContentListId: MediaContentListId => mediaContentListId.asJson
  //    case reviewId: ReviewId => reviewId.asJson
  //    case replyId: ReplyId => replyId.asJson
  //  }
}
