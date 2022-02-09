package com.github.romer533.endpoint

import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

object EndpointOps {

  case class Urls(urls: Seq[String])

  case class Titles(titles: Seq[String])

  val getEndpoint: Endpoint[Unit, Urls, Unit, Titles, Any] =
    endpoint.get.in("titles").in(jsonBody[Urls]).out(jsonBody[Titles])

}
