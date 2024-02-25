package modelClasses

case class Season(
                 id: Int,
                 tvShowId: Int,
                 episodesIds: Seq[Int]
                 // TODO: Meter más campos
                 )
