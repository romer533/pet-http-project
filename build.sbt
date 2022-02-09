val scala3Version = "2.13.6"

ThisBuild / scalaVersion := scala3Version
ThisBuild / scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Wunused:imports,privates,locals,patvars",
  "-Wconf:src=src_managed/.*:silent",
  "-Wdead-code",
  "-Xlint:infer-any",
  "-feature",
  "-language:existentials"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "pet-http-project",
    libraryDependencies ++= Dependencies.all
  )
