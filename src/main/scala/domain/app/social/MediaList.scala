package domain.app.social

import domain.ids.Media.{BookId, MovieId, TvEpisodeNumber, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.{LikeId, MediaListId, ReplyId}
import domain.ids.User.UserId

import java.time.LocalDateTime

case class MediaList(
                      id          : MediaListId,
                      userId      : UserId,
                      title       : String,
                      description : String,
                      mediaIds    : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                      visibility  : Visibility,
                      allowReplies: Boolean,
                      ranked      : Boolean,
                      creationDate: LocalDateTime,
                      updateDate  : LocalDateTime,
                      likesIds    : List[LikeId],
                      repliesIds  : List[ReplyId]
                    )