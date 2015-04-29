name := """play-core"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "commons-io" % "commons-io" % "2.4",
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "info.cukes" % "cucumber-java" % "1.1.8" % "test",
  "info.cukes" % "cucumber-junit" % "1.1.8" % "test"
)

jacoco.settings

findbugsSettings

findbugsReportType := Some(de.johoop.findbugs4sbt.ReportType.Html)

herokuAppName in Compile := "trivial3b"

