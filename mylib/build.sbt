// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val commonSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.13.1",
  organization := "foobar",
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.6.9" % "test"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework"),
  scalacOptions in (Compile, doc) ++= Seq("-groups", "-implicits"),
  scalacOptions += "-Yrangepos"
)

// Shared
lazy val shared =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure)
    .settings(commonSettings: _*)
    .settings(
      name := "shared"
    )
    .jvmSettings(
      libraryDependencies += "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided"
    )

lazy val sharedJS  = shared.js
lazy val sharedJVM = shared.jvm

// Root
lazy val mylibProject =
  (project in file("."))
    .settings(commonSettings: _*)
    .settings(
      publish := {},
      publishLocal := {}
    )
    .aggregate(sharedJVM)
