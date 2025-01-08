package codecs

import sttp.tapir.{Codec, CodecFormat, DecodeResult}
import sttp.tapir.CodecFormat.TextPlain

object ModelClasses {
  
  object Media {
    
    import modelClasses.ids.Media.*
    
    implicit val movieIdCodec: Codec[String, MovieId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(MovieId(s.toLong)))(_.value.toString)

    implicit val tvShowIdCodec: Codec[String, TvShowId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(TvShowId(s.toLong)))(_.value.toString)

    implicit val seasonNumberCodec: Codec[String, SeasonNumber, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(SeasonNumber(s.toLong)))(_.value.toString)

    implicit val episodeNumberCodec: Codec[String, EpisodeNumber, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(EpisodeNumber(s.toLong)))(_.value.toString)

    implicit val bookIdCodec: Codec[String, BookId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(BookId(s)))(_.value)

    implicit val videogameIdCodec: Codec[String, VideogameId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(VideogameId(s.toLong)))(_.value.toString)
  }
  
  object Social {
    
    import modelClasses.ids.Social.*
    
    implicit val entryIdCodec: Codec[String, EntryId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(EntryId(s.toLong)))(_.value.toString)

    implicit val mediaContentListIdCodec: Codec[String, MediaContentListId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(MediaContentListId(s.toLong)))(_.value.toString)

    implicit val reviewIdCodec: Codec[String, ReviewId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(ReviewId(s.toLong)))(_.value.toString)

    implicit val ratingIdCodec: Codec[String, RatingId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(RatingId(s.toLong)))(_.value.toString)

    implicit val likeIdCodec: Codec[String, LikeId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(LikeId(s.toLong)))(_.value.toString)

    implicit val replyIdCodec: Codec[String, ReplyId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(ReplyId(s.toLong)))(_.value.toString)
  }
  
  object User {
    
    import modelClasses.ids.User.UserId
    
    implicit val userIdCodec: Codec[String, UserId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(UserId(s.toLong)))(_.value.toString)
  }
  
  object Chatting { 
    import modelClasses.ids.Chatting.{ChatId, MessageId}
    
    implicit val chatIdCodec: Codec[String, ChatId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(ChatId(s.toLong)))(_.value.toString)
      
    implicit val messageIdCodec: Codec[String, MessageId, TextPlain] =
      Codec.string.mapDecode(s => DecodeResult.Value(MessageId(s.toLong)))(_.value.toString)
  }
  
  
  
  
}
