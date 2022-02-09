package com.github.romer533

import cats.effect.{Async, Concurrent, ExitCode, Sync}
import com.github.romer533.config.Config
import com.github.romer533.server.Http4sServer
import com.github.romer533.service.Service
import net.ruippeixotog.scalascraper.browser.JsoupBrowser

trait MainF {

  def runProgram[F[_]: Async: Concurrent]: F[ExitCode] =
    (for {
      cfg <- Config.load()
      service = Service[F](JsoupBrowser())
      _ <- Http4sServer[F](cfg, service)
    } yield ()).use { _ =>
      Sync[F].never[ExitCode]
    }
}
