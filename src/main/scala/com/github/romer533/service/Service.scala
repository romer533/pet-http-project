package com.github.romer533.service

import cats.Applicative
import cats.data.EitherT
import com.github.romer533.endpoint.EndpointOps._
import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

trait Service[F[_]] {
  def getTitles(urls: Seq[String]): F[Either[Error, Titles]]
}

object Service {
  def apply[F[_]: Applicative](browser: Browser): Service[F] =
    (urls: Seq[String]) => {
      val titles = urls.flatMap(url => browser.get(url) >> texts("h1"))
      EitherT.fromEither(Either.cond(titles.nonEmpty, Titles(titles), Error(400, "Title not found"))).value
    }
}
