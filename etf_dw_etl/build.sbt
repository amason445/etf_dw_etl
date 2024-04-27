val scala2Version = "2.13.13"

lazy val root = project
  .in(file("."))
  .settings(
    name := "etf_dw_etl",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala2Version,

    javaOptions += "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    
    //spark libraries
    libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.4",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.4",

    libraryDependencies += "org.tomlj" % "tomlj" % "1.1.1"
    
  )
