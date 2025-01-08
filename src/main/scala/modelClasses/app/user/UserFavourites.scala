package modelClasses.app.user

import modelClasses.app.media.{Book, Movie, TvShow, Videogame}
import modelClasses.ids.Media.{MovieId, TvShowId, BookId, VideogameId}

case class UserFavourites(
                         movie    : Option[MovieId],
                         tvShow   : Option[TvShowId],
                         videogame: Option[VideogameId],
                         book     : Option[BookId]
                         )
