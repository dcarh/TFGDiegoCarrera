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

class ArticlesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathArticleId: EndpointInput[Int] =
    path[Int]("article_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonArticleListOut: EndpointOutput[List[Article]] =
    jsonBody[List[Article]]

  private val jsonArticleOut: EndpointOutput[Article] =
    jsonBody[Article]


  private val articlesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "articles")

  private val articleBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "article")


  val articlesEnpoint: PublicEndpoint[String, Unit, List[Article], Any] =
    articlesBaseEndpoint
      .in(queryOrderBy)
      .out(jsonArticleListOut)

  val specificArticleEnpoint: PublicEndpoint[Int, Unit, Article, Any] =
    articleBaseEndpoint
      .in(pathArticleId)
      .out(jsonArticleOut)

}
