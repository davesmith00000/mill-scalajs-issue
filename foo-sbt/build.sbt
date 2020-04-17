import scala.sys.process._
import scala.language.postfixOps

lazy val commonSettings = Seq(
  version := "0.0.1",
  scalaVersion := "2.13.1",
  organization := "foobar",
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.6.9" % "test"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework"),
  scalacOptions += "-Yrangepos",
  wartremoverWarnings in (Compile, compile) ++= Warts.all
)

lazy val foo =
  project
    .settings(commonSettings: _*)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      libraryDependencies ++= Seq(
          "foobar" %%% "shared" % "0.0.1-SNAPSHOT"
      )
    )

lazy val fooProject =
  (project in file("."))
    .settings(commonSettings: _*)
    .aggregate(foo)
