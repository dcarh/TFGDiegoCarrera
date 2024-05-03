package api

import io.circe.generic.auto._
import modelClasses.user.User
import modelClasses.media.*
import modelClasses.social.MediaContentList
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class SearchEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val searchBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "search")

  val searchEndpoint: PublicEndpoint[(String, String), Unit, MediaContentList, Any] =
    searchBaseEndpoint
      .name("Search endpoint")
      .description("This endpoint searches any element on the app based on text coincidence")
      .get
      .in(inputs.queryType)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonMediaContentListOut)

  val searchMovieEndpoint: PublicEndpoint[(String, String), Unit, List[Movie], Any] =
    searchBaseEndpoint
      .name("Search movie endpoint")
      .description("This endpoint searches any movie on the app based on text coincidence")
      .get
      .in(inputs.pathMovies)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonMovieListOut)

  val searchTVShowEndpoint: PublicEndpoint[(String, String), Unit, List[TVShow], Any] =
    searchBaseEndpoint
      .name("Search TV show endpoint")
      .description("This endpoint searches any TV show on the app based on text coincidence")
      .get
      .in(inputs.pathTVShows)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonTVShowListOut)

  val searchVideogameEndpoint: PublicEndpoint[(String, String), Unit, List[Videogame], Any] =
    searchBaseEndpoint
      .name("Search videogame endpoint")
      .description("This endpoint searches any videogame on the app based on text coincidence")
      .get
      .in(inputs.pathVideogames)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonVideogameListOut)

  val searchBookEndpoint: PublicEndpoint[(String, String), Unit, List[Book], Any] =
    searchBaseEndpoint
      .name("Search book endpoint")
      .description("This endpoint searches any book on the app based on text coincidence")
      .get
      .in(inputs.pathBooks)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonBookListOut)

  val searchElementListEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    searchBaseEndpoint
      .name("Search list endpoint")
      .description("This endpoint searches any list on the app based on text coincidence")
      .get
      .in(inputs.pathLists)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonListOfElementListOut)

  val searchUserEndpoint: PublicEndpoint[(String, String), Unit, List[User], Any] =
    searchBaseEndpoint
      .name("Search user endpoint")
      .description("This endpoint searches any user on the app based on text coincidence")
      .get
      .in(inputs.pathUsers)
      .in(inputs.queryOrderBy)
      .out(outputs.jsonUserListOut)

}
