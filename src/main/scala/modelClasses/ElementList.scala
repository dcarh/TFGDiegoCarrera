package modelClasses

case class ElementList(
                      id: Int,
                      userId: Int,
                      elementsType: String,
                      elementsIds: Seq[Int],
                      likesIds: Seq[Int],
                      commentsIds: Seq[Int]
                      )
