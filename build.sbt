name := "wanzi-sys"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
"net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
"com.typesafe.play" %% "play-json" % "2.3.4"
)

javaOptions in Test += "-Dconfig.file=conf/test.conf"
