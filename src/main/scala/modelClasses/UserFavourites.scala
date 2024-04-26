package modelClasses

case class UserFavourites(
                         movie    : Movie.Id,
                         tvShow   : TVShow.Id,
                         videogame: Videogame.Id,
                         book     : Book.Id
                         )
