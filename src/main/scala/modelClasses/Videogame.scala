package modelClasses

case class Videogame(
                    id: Videogame.Id,
                    title: String,
                    year: Long,
                    storyLine: String,
                    summary: String,
                    genres: List[Int],
                    category: Int,
                    status: Int,
                    // collections: (String, List[Videogame.Id]),
                    collections: List[Int],
                    franchises: List[Int],
                    externalGames: List[Videogame.Id],
                    gameEngines: List[Int],
                    gameModes: List[Int],
                    involvedCompanies: List[Int], // Al consultar la involvedCompany, debemos recoger el campo company, no id, pues este último se refiere al id del juego
                    platforms: List[Int],
                    playerPerspectives: List[Int],
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
