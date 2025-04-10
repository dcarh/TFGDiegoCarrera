package modelClasses.app.user

import io.circe.generic.auto.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaListId, RatingId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class User(
                 id           : UserId,
                 profile      : UserProfile,
                 favourites   : UserFavourites,
                 completed    : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                 pending      : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                 inProgress   : List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId],
                 onHold       : List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId],
                 dropped      : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                 mediaLists   : List[MediaListId],
                 entries      : List[EntryId],
                 reviews      : List[ReviewId],
                 ratings      : List[RatingId],
                 likes        : List[LikeId],
                 replies      : List[ReplyId],
                 following    : List[UserId],
                 followers    : List[UserId],
                 blocked      : List[UserId],
                 chats        : List[ChatId],
                 archivedChats: List[ChatId]
               )