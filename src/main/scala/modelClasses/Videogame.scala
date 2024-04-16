package modelClasses

case class Videogame(
                    id: Videogame.Id,
                    title: String,
                    year: String,
                    storyLine: String,
                    summary: String,
                    genres: List[String],
                    category: String,
                    collections: (String, List[Videogame.Id]),
                    franchises: List[String],
                    externalGames: List[Videogame.Id],
                    gameEngines: List[String],
                    gameModes: List[String],
                    involvedCompanies: List[String],
                    platforms: List[String],
                    playerPerspectives: List[String],
                    dlcs: List[String],
                    standaloneExpansions: List[Videogame.Id],
                    remakes: List[Videogame.Id],
                    themes: List[String],
                    similarGames: List[String],
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
