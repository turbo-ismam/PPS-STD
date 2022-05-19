
plugins {
    scala
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.6")
    implementation("com.google.guava:guava:30.1.1-jre")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnit("4.13.2")

            dependencies {
                implementation("org.scalatest:scalatest_2.13:3.2.9")
                implementation("org.scalatestplus:junit-4-13_2.13:3.2.2.0")
                runtimeOnly("org.scala-lang.modules:scala-xml_2.13:1.2.0")
            }
        }
    }
}

application {
    mainClass.set("ScalaTowerDefense.App")
}
