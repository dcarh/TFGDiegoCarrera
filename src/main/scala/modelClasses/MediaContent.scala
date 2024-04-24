package modelClasses

import sttp.tapir.generic.auto._

sealed trait MediaContent {
  val id: Element.Id
  val likes: List[Like.Id]
  val reviews: List[Review.Id]
  val averageRating: Double
  val ratings: Long
  val lists: List[MediaContentList.Id]
  val numberOfCompleted: Long
  val numberOfInProgress: Option[Long]
  val numberOfPaused: Option[Long]
  val numberOfPending: Long
  val numberOfAbandoned: Long
}

//case class MediaContent(
//                  id: MediaContent.Id,
//                  elementType: String
//                  )

// TODO: Que Element sea un sealed trait extendido por Movie, TVShow, Season, Episode, Videogame y Book

object MediaContent {
  type Id = Long
}

case class Movie(
                  id: Movie.Id,
                  title: String,
                  director: String,
                  year: String,
                  runtime: Int,
                  overview: String,
                  cast: List[(String, String)],
                  genres: List[(Int, String)],
                  productionCompanies: List[(Int, String)],
                  productionCountries: List[(String, String)],
                  budget: Long,
                  revenue: Long,
                  recommendations: List[Movie.Id],
                  similar: List[Movie.Id],
                  status: String,

                  likes: List[Like.Id],
                  reviews: List[Review.Id],
                  averageRating: Double,
                  ratings: Long,
                  lists: List[MediaContentList.Id],
                  numberOfCompleted: Long,
                  numberOfInProgress: Option[Long],
                  numberOfPaused: Option[Long],
                  numberOfPending: Long,
                  numberOfAbandoned: Long
                ) extends MediaContent

object Movie {
  type Id = Long
}

case class TVShow(
                   id: TVShow.Id,
                   title: String,
                   creator: String,
                   status: String,
                   firstAirDate: String,
                   lastAirDate: String,
                   totalRuntime: Int,
                   episodeRuntime: Int,
                   overview: String,
                   cast: List[(String, String)],
                   genres: List[(Int, String)],
                   productionCompanies: List[(Int, String)],
                   productionCountries: List[(String, String)],
                   numberOfSeasons: Int,
                   numberOfEpisodes: Int,
                   seasonsIds: List[Season.Id],
                   episodesIds: List[Episode.Id],
                   recommendations: List[Movie.Id],
                   similar: List[Movie.Id],

                   likes: List[Like.Id],
                   reviews: List[Review.Id],
                   averageRating: Double,
                   ratings: Long,
                   lists: List[MediaContentList.Id],
                   numberOfCompleted: Long,
                   numberOfInProgress: Option[Long],
                   numberOfPaused: Option[Long],
                   numberOfPending: Long,
                   numberOfAbandoned: Long
                 ) extends MediaContent

object TVShow {
  type Id = Long
}

case class Videogame(
                      id: Videogame.Id,
                      title: String,
                      year: Long,
                      storyLine: String,
                      summary: String,
                      genres: List[Int],
                      category: Int,
                      status: Int,
                      // collections: (String, List[Videogame.Id]),
                      collections: List[Int],
                      franchises: List[Int],
                      externalGames: List[Videogame.Id],
                      gameEngines: List[Int],
                      gameModes: List[Int],
                      involvedCompanies: List[Int], // Al consultar la involvedCompany, debemos recoger el campo company, no id, pues este último se refiere al id del juego
                      platforms: List[Int],
                      playerPerspectives: List[Int],
                      dlcs: List[Long],
                      standaloneExpansions: List[Videogame.Id],
                      remakes: List[Videogame.Id],
                      themes: List[Long],
                      similarGames: List[Videogame.Id],

                      likes: List[Like.Id],
                      reviews: List[Review.Id],
                      averageRating: Double,
                      ratings: Long,

                      lists: List[MediaContentList.Id],
                      numberOfCompleted: Long,
                      numberOfInProgress: Option[Long],
                      numberOfPaused: Option[Long],
                      numberOfPending: Long,
                      numberOfAbandoned: Long
                    ) extends MediaContent

object Videogame {
  type Id = Long
}

case class Book(
                 id: Book.Id,
                 title: String,
                 authors: List[String],
                 description: String,
                 categories: List[String],

                 likes: List[Like.Id],
                 reviews: List[Review.Id],
                 averageRating: Double,
                 ratings: Long,
                 lists: List[MediaContentList.Id],
                 numberOfCompleted: Long,
                 numberOfInProgress: Option[Long],
                 numberOfPaused: Option[Long],
                 numberOfPending: Long,
                 numberOfAbandoned: Long
               ) extends MediaContent

object Book {
  type Id = Long
}