import sbt._
import Keys._

object Build extends Build {

  // lazy val common = Project("common",
  //   base = file("common"))
   //lazy val common =  uri("ssh://git@github.com/isaacloud/common-package.git#%s".format("master"))
  //lazy val common = RootProject(uri("ssh://git@github.com/isaacloud/common-package.git#%s".format("master"))) 
  //lazy val raml = Project("raml-scala-parser", base = file("raml-scala-parser"))

  lazy val defaultSettings =
    Defaults.defaultSettings ++
      Seq(
        name := "sbt-gen",
        version := "1.0",
        scalaVersion := "2.10.1",
        scalacOptions := Seq(
          "-feature",
          "-language:implicitConversions",
          "-language:postfixOps",
          "-unchecked",
          "-deprecation",
          "-encoding", "utf8",
          "-Ywarn-adapted-args"))

  lazy val root = Project("root",
    file("."),
    settings = defaultSettings ++ Seq(
      resolvers ++= Seq(
        "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
        "Maven repository" at "http://morphia.googlecode.com/svn/mavenrepo/"),
      libraryDependencies ++= Seq(
          "junit" % "junit" % "4.11",
  "org.powermock" % "powermock-module-junit4" % "1.5.1",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "org.powermock" % "powermock-core" % "1.5.1",
  "org.powermock" % "powermock-api-mockito" % "1.5.1",
  "org.scalatest" % "scalatest_2.10" % "2.0",
  "org.yaml" % "snakeyaml" % "1.13",
  "net.codingwell" % "scala-guice_2.10" % "4.0.0-beta",
    "com.fasterxml.jackson.core" % "jackson-core" % "2.2.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.2.3",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.3",
  "com.google.inject" % "guice" % "4.0-beta",
  "commons-lang" % "commons-lang" % "2.2",
  "commons-beanutils" % "commons-beanutils" % "1.8.3",
  "commons-io" % "commons-io" % "2.4",
  "org.kitchen-eel" % "json-schema-validator" % "1.4.9",
  "org.fusesource.scalate" % "scalate-core_2.10" % "1.6.1"
)))
    // .aggregate(commonpackage)
   // .aggregate(raml)
  //  .dependsOn(raml)

}