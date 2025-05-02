package server.logics.media

import dummies.repositories.{EntryRepository, MediaListRepository, RatingRepository, UserRepository}
import modelClasses.ids.Media.{BookId, MovieId, TvEpisodeNumber, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{EntryId, MediaListId}
import server.logics.media.MediaAuxClasses.{MediaMetrics, StatusCounts}

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
      completed  = allUsers.flatMap(_.completed).count(_ == mediaId),
      dropped    = allUsers.flatMap(_.dropped).count(_ == mediaId),
      inProgress = allUsers.flatMap(_.inProgress).count(_ == mediaId),
      onHold     = allUsers.flatMap(_.onHold).count(_ == mediaId),
      pending    = allUsers.flatMap(_.pending).count(_ == mediaId)
    )

  private def getAverageRatingForMedia(mediaId:
                                       MovieId
                                         | TvShowId
                                         | (TvShowId, TvSeasonNumber)
                                         | (TvShowId, TvSeasonNumber, TvEpisodeNumber)
                                         | VideogameId
                                         | BookId
                                      ): Option[Double] =
    val allRatingsForMedia = RatingRepository.getAll.filter(_.mediaRatedId == mediaId)

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
    RatingRepository.getAll.count(_.mediaRatedId == mediaId)

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
