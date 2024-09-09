package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.VideogameId
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId}
import modelClasses.app.social.{Like, MediaContentList, Review}

case class Videogame(
                      category            : Int,
                      collections         : List[Int],
                      // collections      : (String, List[Videogame.Id]),
                      dlcs                : List[Long],
                      externalGames       : List[VideogameId],
                      franchises          : List[Int],
                      gameEngines         : List[Int],
                      gameModes           : List[Int],
                      genres              : List[Int],
                      id                  : VideogameId,
                      involvedCompanies   : List[Int], // Al consultar la involvedCompany, debemos recoger el campo company, no id, pues este último se refiere al id del juego
                      platforms           : List[Int],
                      playerPerspectives  : List[Int],
                      remakes             : List[VideogameId],
                      similarGames        : List[VideogameId],
                      standaloneExpansions: List[VideogameId],
                      storyLine           : String,
                      summary             : String,
                      status              : Int,
                      themes              : List[Long],
                      title               : String,
                      year                : Long,

                      averageRating       : Double,
                      likes               : List[LikeId],
                      lists               : List[MediaContentListId],
                      numberOfAbandoned   : Long,
                      numberOfCompleted   : Long,
                      numberOfInProgress  : Option[Long],
                      numberOfPaused      : Option[Long],
                      numberOfPending     : Long,
                      ratings             : Long,
                      reviews             : List[ReviewId],
                    )

// object Videogame {
//   type Id = Long
// }
