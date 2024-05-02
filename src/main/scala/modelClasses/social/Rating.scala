package modelClasses.social

import modelClasses.media.*

case class Rating(
                 mediaRated: Either[MediaMainContent.Id, MediaSecondaryContent.Id],
                 rating: Int
                 )

// TODO: ¿Realmente hace falta esta clase cuando ya tenemos al atributo "rating" en Entry?