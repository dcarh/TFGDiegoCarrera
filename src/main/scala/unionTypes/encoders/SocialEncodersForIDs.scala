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

  //  implicit val mediaUnionEncoder5: Encoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId | MediaContentListId | ReviewId | ReplyId] = Encoder.instance {
  //    case movieId: MovieId => movieId.asJson
  //    case tvShowId: TVShowId => tvShowId.asJson
  //    case seasonNumber: (TVShowId, SeasonNumber) => seasonNumber.asJson
  //    case episodeNumber: (TVShowId, SeasonNumber, EpisodeNumber) => episodeNumber.asJson
  //    case videogameId: VideogameId => videogameId.asJson
  //    case bookId: BookId => bookId.asJson
  //    case mediaContentListId: MediaContentListId => mediaContentListId.asJson
  //    case reviewId: ReviewId => reviewId.asJson
  //    case replyId: ReplyId => replyId.asJson
  //  }
}
