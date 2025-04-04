package server.logics.media

import dummies.repositories.UserRepository
import modelClasses.ids.Media.{BookId, MovieId, TvEpisodeNumber, TvSeasonNumber, TvShowId, VideogameId}

object MediaAuxFunctions {

  def getStatusCountForMedia(mediaId: MovieId |
                                      TvShowId |
                                      (TvShowId, TvSeasonNumber) |
                                      (TvShowId, TvSeasonNumber, TvEpisodeNumber) |
                                      VideogameId |
                                      BookId,
                             status: String): Long =
    val allUsers = UserRepository.getAll

    val allMediaByStatus = status match
      case "completed" => allUsers.flatMap(_.completed)
      case "dropped" => allUsers.flatMap(_.dropped)
      case "inProgress" => allUsers.flatMap(_.inProgress)
      case "onHold" => allUsers.flatMap(_.onHold)
      case "pending" => allUsers.flatMap(_.pending)

    allMediaByStatus.count(_ == mediaId)


  def getCompletionsForMedia2(mediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId): Long =
    val allUsers = UserRepository.getAll
    val allCompletions = allUsers.flatMap(_.completed)

    allCompletions.count {
      case MovieId(value) if mediaId == MovieId(value) => true
      case TvShowId(value) if mediaId == TvShowId(value) => true
      case (TvShowId(showId), TvSeasonNumber(season)) if mediaId == (TvShowId(showId), TvSeasonNumber(season)) => true
      case (TvShowId(showId), TvSeasonNumber(season), TvEpisodeNumber(episode)) if mediaId == (TvShowId(showId), TvSeasonNumber(season), TvEpisodeNumber(episode)) => true
      case VideogameId(value) if mediaId == VideogameId(value) => true
      case BookId(value) if mediaId == BookId(value) => true
      case _ => false
    }

}
