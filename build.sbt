name := "scala-overpunch"

version := "2.0.0"

organization := "com.github.log0ymxm"

homepage := Some(url("https://github.com/log0ymxm/scala-overpunch"))

licenses += ("MIT", url("https://github.com/log0ymxm/scala-overpunch/blob/master/LICENSE"))

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

scmInfo := Some(ScmInfo(url("https://github.com/log0ymxm/scala-overpunch"), "git@github.com:log0ymxm/scala-overpunch.git"))

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

pomIncludeRepository := { _ => false }

pomExtra :=
  <developers>
    <developer>
      <id>log0ymxm</id>
      <name>Paul English</name>
      <url>http://github.com/log0ymxm</url>
    </developer>
  </developers>
