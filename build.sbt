import sbt._
import Keys._

name := """play-slick-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

routesGenerator := InjectedRoutesGenerator

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-slick" % "2.0.0",
    "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
    "com.h2database" % "h2" % "1.4.187",
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test",
    specs2 % Test,
    filters,
    //  jdbc,
    "org.webjars" %% "webjars-play" % "2.3.0",
    //  "org.webjars" % "angularjs" % "1.2.19",
    "org.webjars" % "bootstrap" % "3.2.0" exclude("org.webjars", "jquery"),
    "org.webjars" % "requirejs" % "2.1.14-1" exclude("org.webjars", "jquery"),
    "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
    "com.typesafe.play" %% "play-mailer" % "5.0.0-M1"
)

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

fork in run := true


//includeFilter in (Assets, LessKeys.less) := "*.less"


//herokuAppName in Compile := "whispering-sands-84392"