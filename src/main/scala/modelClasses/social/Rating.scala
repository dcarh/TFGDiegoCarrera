package modelClasses.social

import modelClasses.media._

case class Rating(
                 // mediaRated: Either[MediaMainContent.Id, MediaSecondaryContent.Id],
                 mediaRated: Movie.Id | TVShow.Id | Season.Id | Episode.Id | Videogame.Id | Book.Id,
                 rating: Int
                 )

// TODO: ¿Realmente hace falta esta clase cuando ya tenemos al atributo "rating" en Entry?