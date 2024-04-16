package modelClasses

case class Videogame(
                    id: Videogame.Id,
                    title: String,
                    year: String,
                    storyLine: String,
                    summary: String,
                    genres: Seq[String],
                    category: String,
                    collections: (String, Seq[Videogame.Id]),
                    franchises: Seq[String],
                    externalGames: Seq[Videogame.Id],
                    gameEngines: Seq[String],
                    gameModes: Seq[String],
                    involvedCompanies: Seq[String],
                    platforms: Seq[String],
                    playerPerspectives: Seq[String],
                    dlcs: Seq[String],
                    standaloneExpansions: Seq[Videogame.Id],
                    remakes: Seq[Videogame.Id],
                    themes: Seq[String],
                    similarGames: Seq[String],
                    // TODO: Echar un vistazo a la mayoría de atributos: ¿String o Id o case class específica para estos?

                    likes: Seq[Like.Id],
                    reviews: Seq[Review.Id],
                    averageRating: Double,
                    ratings: Long,

                    lists: Seq[ElementList.Id],
                    completed: Long,
                    inProgress: Long,
                    pending: Long,
                    abandoned: Long
                    ) extends Element

object Videogame {
  type Id = Long
}
