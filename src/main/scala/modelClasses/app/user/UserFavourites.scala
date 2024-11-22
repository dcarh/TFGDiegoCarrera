package modelClasses.app.user

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.ids.Media.{MovieId, TVShowId, BookId, VideogameId}

case class UserFavourites(
                         movie    : Option[MovieId],
                         tvShow   : Option[TVShowId],
                         videogame: Option[VideogameId],
                         book     : Option[BookId]
                         )
