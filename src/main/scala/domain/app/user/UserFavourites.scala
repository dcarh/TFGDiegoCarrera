package domain.app.user

import domain.ids.Media.{MovieId, TvShowId, BookId, VideogameId}

case class UserFavourites(
                           movieId    : Option[MovieId],
                           tvShowId   : Option[TvShowId],
                           videogameId: Option[VideogameId],
                           bookId     : Option[BookId]
                         )
