package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.VideogameId
import modelClasses.ids.Social.{LikeId, MediaListId, ReviewId}
import modelClasses.app.social.{Like, MediaList, Review}

case class Videogame(
                      category            : Long,
                      collection          : Option[Long],
                      collections         : List[Long],
                      // collections      : (String, List[Videogame.Id]),
                      dlcs                : Option[List[Long]],
                      externalGames       : List[Long],
                      firstReleaseDate    : Long,
                      franchises          : List[Long],
                      gameEngines         : List[Long],
                      gameModes           : List[Long],
                      genres              : List[Long],
                      id                  : VideogameId,
                      involvedCompanies   : List[Long], // Al consultar la involvedCompany, debemos recoger el campo company, no id, pues este último se refiere al id del juego
                      parentGame          : Option[Long],
                      platforms           : List[Long],
                      playerPerspectives  : List[Long],
                      remakes             : List[Long],
                      similarGames        : List[Long],
                      standaloneExpansions: Option[List[Long]],
                      storyLine           : String,
                      summary             : String,
                      themes              : List[Long],
                      title               : String,
                      year                : Long,

//                      averageRating       : Double,
//                      lists               : List[MediaListId],
//                      numberOfAbandoned   : Long,
//                      numberOfCompleted   : Long,
//                      numberOfInProgress  : Option[Long],
//                      numberOfPaused      : Option[Long],
//                      numberOfPending     : Long,
//                      ratings             : Long,
//                      reviews             : List[ReviewId],
                    )
