name := "my-first-couchbase-spark-project"

organization := "my.organization"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.1",
  "org.apache.spark" %% "spark-streaming" % "1.4.1",
  "org.apache.spark" %% "spark-sql" % "1.4.1",
  "com.couchbase.client" %% "spark-connector" % "1.0.1"
)