package modelClasses.app.user

import modelClasses.ids.Media.{MovieId, TvShowId, BookId, VideogameId}

case class UserFavourites(
                         movie    : Option[MovieId],
                         tvShow   : Option[TvShowId],
                         videogame: Option[VideogameId],
                         book     : Option[BookId]
                         )
