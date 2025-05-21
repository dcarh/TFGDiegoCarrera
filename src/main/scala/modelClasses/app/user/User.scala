package modelClasses.app.user

import io.circe.generic.auto.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaListId, RatingId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class User(
                 id                : UserId,
                 profile           : UserProfile,
                 favourites        : UserFavourites,
                 completedMediaIds : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                 pendingMediaIds   : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                 inProgressMediaIds: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId],
                 onHoldMediaIds    : List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId],
                 droppedMediaIds   : List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
                 mediaListsIds     : List[MediaListId],
                 entriesIds        : List[EntryId],
                 reviewsIds        : List[ReviewId],
                 ratingsIds        : List[RatingId],
                 likesIds          : List[LikeId],
                 repliesIds        : List[ReplyId],
                 followingIds      : List[UserId],
                 followersIds      : List[UserId],
                 blockedIds        : List[UserId],
                 chatsIds          : List[ChatId],
                 archivedChatsIds  : List[ChatId]
               )