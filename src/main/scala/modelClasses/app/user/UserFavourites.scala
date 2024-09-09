package modelClasses.app.user

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.ids.Media.{MovieId, TVShowId, BookId, VideogameId}

case class UserFavourites(
                         movie    : MovieId,
                         tvShow   : TVShowId,
                         videogame: VideogameId,
                         book     : BookId
                         )
