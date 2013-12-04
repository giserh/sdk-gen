
name := "sdk-gen"

version := "1.0"


resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Maven repository" at "http://morphia.googlecode.com/svn/mavenrepo/"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11",
  "org.powermock" % "powermock-module-junit4" % "1.5.1",
  "org.powermock" % "powermock-core" % "1.5.1",
  "org.powermock" % "powermock-api-mockito" % "1.5.1",
  "org.scalatest" % "scalatest_2.10" % "2.0",
  "net.codingwell" % "scala-guice_2.10" % "4.0.0-beta",
  "com.google.inject" % "guice" % "4.0-beta"
)