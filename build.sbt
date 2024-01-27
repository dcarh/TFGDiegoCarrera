ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "TFGDiegoCarrera" ,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.9.7",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.9.7",
      "io.circe" %% "circe-generic" % "0.14.5",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.typelevel" %% "cats-effect" % "3.5.0"
    ),
    scalacOptions += "-Ypartial-unification"
  )
