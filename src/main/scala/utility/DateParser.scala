package utility

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.Try

object DateParser {

  private val fullDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def parseDate(opt: Option[String]): Option[LocalDate] = {
    opt match {
      case Some(s) if s.matches("\\d{4}-\\d{2}-\\d{2}") =>
        Try(LocalDate.parse(s, fullDateFormat)).toOption
      case Some(s) if s.matches("\\d{4}-\\d{2}") =>
        Try(LocalDate.parse(s + "-01", fullDateFormat)).toOption
      case Some(s) if s.matches("\\d{4}") =>
        Try(LocalDate.parse(s + "-01-01", fullDateFormat)).toOption
      case _ => None
    }
  }
}
