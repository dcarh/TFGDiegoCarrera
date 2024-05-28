package modelClasses.user

import modelClasses.media._

case class UserFavourites(
                         movie    : Movie.Id,
                         tvShow   : TVShow.Id,
                         videogame: Videogame.Id,
                         book     : Book.Id
                         )
