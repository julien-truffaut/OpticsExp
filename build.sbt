
import sbt._

name := "Vanilla"

version := "0.1"

scalaVersion := "2.11.7"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "org.scalatest"   %% "scalatest"   % "2.2.4"  % "test"
)

