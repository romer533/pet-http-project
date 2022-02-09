package com.github.romer533.endpoint

import io.circe.generic.auto._
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

object EndpointOps {

  case class Urls(urls: Seq[String])

  case class Titles(titles: Seq[String])

  case class Error(code: Int, message: String)

  val getEndpoint: Endpoint[Unit, Urls, Error, Titles, Any] =
    endpoint.get.in("titles").in(jsonBody[Urls]).errorOut(statusCode(StatusCode.unsafeApply(400)).and(jsonBody[Error])).out(jsonBody[Titles])

}
