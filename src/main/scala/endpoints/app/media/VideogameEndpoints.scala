package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.MediaBaseEndpoint.mediaBaseEndpoint
import endpoints.io.inputs.Common.*
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.ids.Media.*

object VideogameEndpoints {

  private val getVideogameBaseEndpoint:
    (String, String) => PublicEndpoint[VideogameId, UserError, Unit, Any] =
    (name, description) => mediaBaseEndpoint(name, description)
      .in("videogame")
      .in(PathInputs.pathVideogameId)

  val getVideogame: PublicEndpoint[VideogameId, UserError, Videogame, Any] =
    getVideogameBaseEndpoint(
      "getVideogame",
      "This endpoint returns a videogame by its ID",
    )
      .out(MediaOutputs.videogameOutput)

  val getEntriesForVideogame: PublicEndpoint[VideogameId, UserError, List[Entry], Any] =
    getVideogameBaseEndpoint(
      "getEntriesForVideogame",
      "This endpoint returns a list of all the entries for a videogame",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForVideogame: PublicEndpoint[VideogameId, UserError, List[MediaList], Any] =
    getVideogameBaseEndpoint(
      "getMediaListsForVideogame",
      "This endpoint returns a list of all the media lists for a videogame",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
