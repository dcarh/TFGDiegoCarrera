package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.MediaBaseEndpoint.mediaBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, MediaListId}

object VideogameEndpoints {

  private val getVideogameBaseEndpoint:
    (String, String, String) => PublicEndpoint[VideogameId, UserError, Unit, Any] =
    (name, description, method) => mediaBaseEndpoint(name, description, method)
      .in("videogame")
      .in(PathInputs.pathVideogameId)

  val getVideogame: PublicEndpoint[VideogameId, UserError, Videogame, Any] =
    getVideogameBaseEndpoint(
      "getVideogame",
      "This endpoint returns the videogame specified by the ID introduced",
      "GET"
    )
      .out(MediaOutputs.videogameOutput)

  val getEntriesForVideogame: PublicEndpoint[(VideogameId, List[EntryId]), UserError, List[Entry], Any] =
    getVideogameBaseEndpoint(
      "getEntriesForVideogame",
      "This endpoint returns a list of all the entries for a specific videogame",
      "POST_IGDB"
    )
      .in("entries")
      .in(JsonInputs.jsonListOfEntriesIds)
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForVideogame: PublicEndpoint[(VideogameId, List[MediaListId]), UserError, List[MediaList], Any] =
    getVideogameBaseEndpoint(
      "getMediaListsForVideogame",
      "This endpoint returns a list of all the media lists for a specific videogame",
      "POST_IGDB"
    )
      .in("media_lists")
      .in(JsonInputs.jsonListOfMediaListsIds)
      .out(SocialOutputs.listOfMediaListsOutput)

}
