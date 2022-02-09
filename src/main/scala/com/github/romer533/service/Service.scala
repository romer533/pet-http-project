package com.github.romer533.service

import cats.Applicative
import cats.implicits.{catsSyntaxApplicativeId, catsSyntaxEitherId}
import com.github.romer533.endpoint.EndpointOps.Titles
import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

trait Service[F[_]] {
  def getTitles(urls: Seq[String]): F[Either[Unit, Titles]]
}

object Service {
  def apply[F[_]: Applicative](browser: Browser): Service[F] =
    (urls: Seq[String]) =>
      Titles(urls.map { url =>
        val doc = browser.get(url)
        doc >> text("h1")
      }).asRight[Unit].pure[F]
}
