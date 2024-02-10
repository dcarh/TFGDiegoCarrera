package api

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import model.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Log, Comment, Article,
  Settings, Review, ErrorInfo, Chat}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword
import sttp.model.StatusCode

import java.util.UUID

class VideogamesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathVideogameId: EndpointInput[Int] =
    path[Int]("videogame_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonVideogameListOut: EndpointOutput[List[Videogame]] =
    jsonBody[List[Videogame]]

  private val jsonVideogameOut: EndpointOutput[Videogame] =
    jsonBody[Videogame]


  private val videogamesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "videogames")


  val videogamesEnpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    videogamesBaseEndpoint
      .in(queryOrderBy)
      .out(jsonVideogameListOut)

  val specificVideogameEnpoint: PublicEndpoint[Int, Unit, Videogame, Any] =
    videogamesBaseEndpoint
      .in(pathVideogameId)
      .out(jsonVideogameOut)
  
}
