package api

import io.circe.generic.auto._
import modelClasses.Article
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class ArticlesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathArticleId: EndpointInput[Int] =
    path[Int]("article_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonArticleListOut: EndpointOutput[Seq[Article]] =
    jsonBody[Seq[Article]]

  private val jsonArticleOut: EndpointOutput[Article] =
    jsonBody[Article]


  private val articlesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "articles")

  private val articleBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "article")


  val articlesEndpoint: PublicEndpoint[String, Unit, Seq[Article], Any] =
    articlesBaseEndpoint
      .name("Articles endpoint")
      .description("This endpoint returns a list with all the articles in the app")
      .get
      .in(queryOrderBy)
      .out(jsonArticleListOut)

  val specificArticleEndpoint: PublicEndpoint[Int, Unit, Article, Any] =
    articleBaseEndpoint
      .name("Specific article endpoint")
      .description("This endpoint returns a specific article by its Id")
      .get
      .in(pathArticleId)
      .out(jsonArticleOut)

}
