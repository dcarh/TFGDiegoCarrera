package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{MediaListId, ReviewId}
import modelClasses.igdb.VideogameRequests.VideogameAllFields

case class Videogame(
                      requestedVideogame: VideogameAllFields,
//                      category            : Option[Long],
//                      collection          : Option[Long],
//                      collections         : Option[List[Long]],
//                      // collections      : (String, List[Videogame.Id]),
//                      dlcs                : Option[List[Long]],
//                      externalGames       : Option[List[Long]],
//                      firstReleaseDate    : Option[Long],
//                      franchises          : Option[List[Long]],
//                      gameEngines         : Option[List[Long]],
//                      gameModes           : Option[List[Long]],
//                      genres              : Option[List[Long]],
//                      id                  : VideogameId,
//                      involvedCompanies   : Option[List[Long]], // Al consultar la involvedCompany, debemos recoger el campo company, no id, pues este último se refiere al id del juego
//                      parentGame          : Option[Long],
//                      platforms           : Option[List[Long]],
//                      playerPerspectives  : Option[List[Long]],
//                      remakes             : Option[List[Long]],
//                      similarGames        : Option[List[Long]],
//                      standaloneExpansions: Option[List[Long]],
//                      storyLine           : Option[String],
//                      summary             : Option[String],
//                      themes              : Option[List[Long]],
//                      title               : String,
//                      year                : Option[Long],

//                      averageRating       : Double,
//                      lists               : List[MediaListId],
                      numberOfCompleted   : Long,
                      numberOfDropped     : Long,
                      numberOfInProgress  : Long,
                      numberOfOnHold      : Long,
                      numberOfPending     : Long,
//                      ratings             : Long,
//                      reviews             : List[ReviewId],
                    )
