package modelClasses

case class Videogame(
                    id: Videogame.Id,
                    title: String,
                    year: Long,
                    storyLine: String,
                    summary: String,
                    genres: List[Long],
                    category: Long,
                    // collections: (String, List[Videogame.Id]),
                    collections: List[Long],
                    franchises: List[Long],
                    externalGames: List[Videogame.Id],
                    gameEngines: List[Long],
                    gameModes: List[Long],
                    involvedCompanies: List[Long],
                    platforms: List[Long],
                    playerPerspectives: List[Long],
                    dlcs: List[Long],
                    standaloneExpansions: List[Videogame.Id],
                    remakes: List[Videogame.Id],
                    themes: List[Long],
                    similarGames: List[Videogame.Id],
                    // TODO: Echar un vistazo a la mayoría de atributos: ¿String o Id o case class específica para estos?

                    likes: List[Like.Id],
                    reviews: List[Review.Id],
                    averageRating: Double,
                    ratings: Long,

                    lists: List[ElementList.Id],
                    completed: Long,
                    inProgress: Long,
                    pending: Long,
                    abandoned: Long
                    ) extends Element

object Videogame {
  type Id = Long
}
