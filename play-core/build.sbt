name := """play-core"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "jquery" % "2.1.3",
  "commons-io" % "commons-io" % "2.4",
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
  "info.cukes" % "cucumber-java" % "1.1.8" % "test",
  "info.cukes" % "cucumber-junit" % "1.1.8" % "test"
)
