name := "MagmaKernelJava"

version := "2.0.1"

mainClass := Some("com.meti.core.Main")

// https://mvnrepository.com/artifact/com.google.inject/guice
libraryDependencies += "com.google.inject" % "guice" % "4.2.3"

libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.+" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-engine" % "5.+" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-params" % "5.+" % Test
