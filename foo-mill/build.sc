import $ivy.`com.lihaoyi::mill-contrib-bloop:$MILL_VERSION`
import mill._
import mill.scalalib._
import mill.scalajslib._
import coursier.maven.MavenRepository

object foo extends ScalaJSModule {
  def scalaVersion   = "2.13.1"
  def scalaJSVersion = "0.6.32"

  def ivyDeps = Agg(
    ivy"foobar::shared::0.0.1-SNAPSHOT"
  )

  def repositories = super.repositories ++ Seq(
    MavenRepository("https://oss.sonatype.org/content/repositories/releases")
  )

  def scalacOptions = Seq("-P:wartremover:only-warn-traverser:org.wartremover.warts.Unsafe")

  // This line breaks the Scala.js fastOpt build.
  def scalacPluginIvyDeps = Agg(ivy"org.wartremover:::wartremover:2.4.5")

  object test extends Tests {
    def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.7.1")

    def testFrameworks = Seq("utest.runner.Framework")
  }

}
