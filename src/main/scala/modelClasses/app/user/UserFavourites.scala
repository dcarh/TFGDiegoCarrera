package modelClasses.app.user

import modelClasses.app.media.{Book, Movie, TVShow, Videogame}

case class UserFavourites(
                         movie    : Movie.Id,
                         tvShow   : TVShow.Id,
                         videogame: Videogame.Id,
                         book     : Book.Id
                         )
