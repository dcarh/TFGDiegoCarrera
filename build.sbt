ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "TFGDiegoCarrera" ,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.9.11",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.9.11",
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.9.11",
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-client" % "1.10.8",
      "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % "1.10.8",
      "org.http4s" %% "http4s-blaze-server" % "0.23.16",
      "org.http4s" %% "http4s-blaze-client" % "0.23.16",
      "org.http4s" %% "http4s-circe" % "0.23.26",
      "org.http4s" %% "http4s-dsl" % "0.23.27",
      "ch.qos.logback" % "logback-classic" % "1.5.6",
      // "io.circe" %% "circe-generic" % "0.14.5",
      "org.typelevel" %% "cats-core" % "2.12.0",
      // "org.typelevel" %% "cats-effect" % "3.5.0",
      //"com.softwaremill.sttp.tapir" %% "tapir-http4s-client" % "1.9.7"
      "io.circe" %% "circe-core" % "0.14.7",
      "io.circe" %% "circe-generic" % "0.14.7",
      "io.circe" %% "circe-parser" % "0.14.7",
      "org.typelevel" %% "cats-effect" % "3.5.0",
      "com.softwaremill.sttp.client3" %% "http4s-backend" % "3.9.6",
      "org.http4s" %% "http4s-ember-client" % "0.23.27",
      "com.lihaoyi" %% "upickle" % "3.3.0",
      "com.lihaoyi" %% "os-lib" % "0.10.1"
    ),
    scalacOptions += "-Xmax-inlines:100"
    // fork/run := true, // Asegúrate de que la aplicación se ejecute en un proceso separado
    // javaOptions += "-Xss1024m"
  )
