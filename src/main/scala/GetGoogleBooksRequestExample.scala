import cats.effect.*
import clients.GoogleBooksClient
import endpoints.googleBooks.Books
import modelClasses.ids.Media.BookId

object GetGoogleBooksRequestExample extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
//    GoogleBooksClient.executeRequest(Books.searchBooksEndpoint2, ("Foundation+inauthor:Asimov", "relevance", "lite", "en")).flatMap {
//      case Right(resource) =>
//        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
//      case Left(error) =>
//        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
//    }

    GoogleBooksClient.executeRequest(Books.searchBooksEndpoint, ("Dune+inauthor:Herbert", "relevance")).flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }

//    GoogleBooksClient.executeRequest(Books.requestBookEndpoint, BookId("UU-VAAAACAAJ")).flatMap {
//      case Right(resource) =>
//        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
//      case Left(error) =>
//        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
//    }
  }
}