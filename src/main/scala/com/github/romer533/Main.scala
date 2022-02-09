package com.github.romer533

import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp with MainF {
  override def run(args: List[String]): IO[ExitCode] =
    runProgram[IO]
}
