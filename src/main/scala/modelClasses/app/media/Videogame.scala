package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.app.social.{Like, MediaContentList, Review}

case class Videogame(
                      category            : Int,
                      collections         : List[Int],
                      // collections      : (String, List[Videogame.Id]),
                      dlcs                : List[Long],
                      externalGames       : List[Videogame.Id],
                      franchises          : List[Int],
                      gameEngines         : List[Int],
                      gameModes           : List[Int],
                      genres              : List[Int],
                      id                  : Videogame.Id,
                      involvedCompanies   : List[Int], // Al consultar la involvedCompany, debemos recoger el campo company, no id, pues este último se refiere al id del juego
                      platforms           : List[Int],
                      playerPerspectives  : List[Int],
                      remakes             : List[Videogame.Id],
                      similarGames        : List[Videogame.Id],
                      standaloneExpansions: List[Videogame.Id],
                      storyLine           : String,
                      summary             : String,
                      status              : Int,
                      themes              : List[Long],
                      title               : String,
                      year                : Long,

                      averageRating       : Double,
                      likes               : List[Like.Id],
                      lists               : List[MediaContentList.Id],
                      numberOfAbandoned   : Long,
                      numberOfCompleted   : Long,
                      numberOfInProgress  : Option[Long],
                      numberOfPaused      : Option[Long],
                      numberOfPending     : Long,
                      ratings             : Long,
                      reviews             : List[Review.Id],
                    )

object Videogame {
  type Id = Long
}
