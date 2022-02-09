import sbt._

object Dependencies {

  object Version {
    val catsCore   = "2.7.0"
    val catsEffect = "3.3.5"
    val slf4j      = "1.7.35"
    val tapir      = "0.19.3"
    val sttpClient = "3.4.1"
    val scraper    = "2.2.1"
    val pureConfig = "0.17.1"
  }

  val all: Seq[ModuleID] = Seq(
    "org.typelevel"                 %% "cats-core"                      % Version.catsCore,
    "org.typelevel"                 %% "cats-effect"                    % Version.catsEffect,
    "org.slf4j"                      % "slf4j-api"                      % Version.slf4j,
    "org.slf4j"                      % "slf4j-simple"                   % Version.slf4j,
    "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server"            % Version.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-sttp-client"              % Version.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"               % Version.tapir,
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % Version.sttpClient,
    "net.ruippeixotog"              %% "scala-scraper"                  % Version.scraper,
    "com.github.pureconfig"         %% "pureconfig"                     % Version.pureConfig
  )

}
