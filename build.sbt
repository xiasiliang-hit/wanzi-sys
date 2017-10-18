import play.PlayJava

name := "wanzi-sys"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
"net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0"
)

libraryDependencies += "com.google.code.gson" % "gson" % "2.2"

libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.3.10"

javaOptions in Test += "-Dconfig.file=conf/test.conf"
