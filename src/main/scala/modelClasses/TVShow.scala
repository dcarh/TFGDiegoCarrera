package modelClasses

case class TVShow(
                 id: Int,
                 seasonsIds: Seq[Int],
                 episodesIds: Seq[Int]
                 // TODO: Meter más campos
                 )
