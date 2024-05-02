package modelClasses.media

import modelClasses.social.{Like, Review}

sealed trait MediaSecondaryContent {
  val id                : MediaSecondaryContent.Id
  val likes             : List[Like.Id]
  val reviews           : List[Review.Id]
  val averageRating     : Double
  val ratings           : Long
}

object MediaSecondaryContent {
  type Id = Long
}

case class Season(
                   id: Season.Id,
                   title: String,
                   airDate: String,
                   seasonNumber: Int,
                   overview: String,
                   tvShowId: TVShow.Id,
                   numberOfEpisodes: Int,
                   episodesIds: List[Episode.Id],

                   likes: List[Like.Id],
                   reviews: List[Review.Id],
                   averageRating: Double,
                   ratings: Long,
                   completed: Long,
                   inProgress: Long,
                   paused: Long,
                   pending: Long,
                   abandoned: Long
                 ) extends MediaSecondaryContent

object Season {
  type Id = Long
}

case class Episode(
                    id           : Episode.Id,
                    tvShowId     : TVShow.Id,
                    seasonId     : Season.Id,
                    title        : String,
                    airDate      : String,
                    seasonNumber : Int,
                    overview     : String,
                    episodeNumber: Int,
                    crew         : List[(String, String)],
                    guestStars   : List[(String, String)],

                    likes        : List[Like.Id],
                    reviews      : List[Review.Id],
                    averageRating: Double,
                    ratings      : Long,
                    completed    : Long,
                    // TODO: Meter más campos
                  ) extends MediaSecondaryContent

object Episode {
  type Id = Long
}