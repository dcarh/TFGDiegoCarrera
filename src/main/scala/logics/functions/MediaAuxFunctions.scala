package logics.functions

import domain.ids.Media.*
import domain.ids.Social.{EntryId, MediaListId}
import logics.media.MediaAuxClasses.{MediaMetrics, StatusCounts}
import memory.repositories.{EntryRepository, MediaListRepository, RatingRepository, UserRepository}

object MediaAuxFunctions {

  def getMetricsForMedia(mediaId:
                         MovieId
                           | TvShowId
                           | (TvShowId, TvSeasonNumber)
                           | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                           | VideogameId
                           | BookId
                        ): MediaMetrics =
    val averageRating = getAverageRatingForMedia(mediaId)
    val entriesIds    = getEntriesIdsForMedia(mediaId)
    val listsIds      = getListsIdsForMedia(mediaId)
    val statusCounts  = getStatusCountsForMedia(mediaId)
    val totalRatings  = getTotalRatingsForMedia(mediaId)

    MediaMetrics(
      averageRating = averageRating, 
      entriesIds    = entriesIds,
      mediaListsIds = listsIds,
      statusCounts  = statusCounts,
      totalRatings  = totalRatings
    )

  private def getStatusCountsForMedia(mediaId:
                                      MovieId
                                        | TvShowId
                                        | (TvShowId, TvSeasonNumber)
                                        | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                                        | VideogameId
                                        | BookId
                                        ): StatusCounts =
    val allUsers = UserRepository.getAll

    StatusCounts(
      completed  = allUsers.flatMap(_.completedMediaIds).count(_ == mediaId),
      dropped    = allUsers.flatMap(_.droppedMediaIds).count(_ == mediaId),
      inProgress = allUsers.flatMap(_.inProgressMediaIds).count(_ == mediaId),
      onHold     = allUsers.flatMap(_.onHoldMediaIds).count(_ == mediaId),
      pending    = allUsers.flatMap(_.pendingMediaIds).count(_ == mediaId)
    )

  private def getAverageRatingForMedia(mediaId:
                                       MovieId
                                         | TvShowId
                                         | (TvShowId, TvSeasonNumber)
                                         | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                                         | VideogameId
                                         | BookId
                                      ): Option[Double] =
    val allRatingsForMedia = RatingRepository.getAll.filter(_.ratedMediaId == mediaId)

    if (allRatingsForMedia.nonEmpty) Some(allRatingsForMedia.map(_.rating).sum.toDouble / allRatingsForMedia.length)
    else None

  private def getTotalRatingsForMedia(mediaId:
                                      MovieId
                                        | TvShowId
                                        | (TvShowId, TvSeasonNumber)
                                        | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                                        | VideogameId
                                        | BookId
                                     ): Long =
    RatingRepository.getAll.count(_.ratedMediaId == mediaId)

  private def getListsIdsForMedia(mediaId:
                                  MovieId
                                    | TvShowId
                                    | (TvShowId, TvSeasonNumber)
                                    | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                                    | VideogameId
                                    | BookId
                                 ): Option[List[MediaListId]] =
    val mediaListsIdsForMedia = MediaListRepository
      .getAll
      .filter(_.mediaIds.contains(mediaId))
      .map(_.id)

    if (mediaListsIdsForMedia.nonEmpty) Some(mediaListsIdsForMedia)
    else None

  private def getEntriesIdsForMedia(mediaId:
                                    MovieId
                                      | TvShowId
                                      | (TvShowId, TvSeasonNumber)
                                      | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                                      | VideogameId
                                      | BookId
                                   ): Option[List[EntryId]] =
    val entriesIdsForMedia = EntryRepository
      .getAll
      .filter(_.mediaId == mediaId)
      .map(_.id)

    if (entriesIdsForMedia.nonEmpty) Some(entriesIdsForMedia)
    else None

}
