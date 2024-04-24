ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "TFGDiegoCarrera" ,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.9.11",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.9.11",
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.9.11",
      "org.http4s" %% "http4s-blaze-server" % "0.23.16",
      "ch.qos.logback" % "logback-classic" % "1.4.14"
      // "io.circe" %% "circe-generic" % "0.14.5",
      // "org.typelevel" %% "cats-core" % "2.9.0",
      // "org.typelevel" %% "cats-effect" % "3.5.0",
      //"com.softwaremill.sttp.tapir" %% "tapir-http4s-client" % "1.9.7"
    ),
    scalacOptions += "-Xmax-inlines:100"
  )
