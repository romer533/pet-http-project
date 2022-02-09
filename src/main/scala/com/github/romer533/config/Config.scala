package com.github.romer533.config

import cats.effect.{Resource, Sync}
import cats.syntax.monadError._
import org.slf4j.LoggerFactory
import pureconfig.ConfigSource
import pureconfig.generic.auto._

import scala.util.Try

final case class Config(host: String, port: Int)

object Config {
  private val log = LoggerFactory.getLogger(getClass)
  def load[F[_]: Sync](): Resource[F, Config] =
    Resource.eval {
      Sync[F]
        .fromTry(Try {
          ConfigSource.default.loadOrThrow[Config]
        })
        .attemptTap(config => Sync[F].delay(log.info(s"Dump configuration: $config")))
    }
}
