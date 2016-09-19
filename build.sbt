lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats" % "0.7.2",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
    )
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "hmrc-code-test"
  )
