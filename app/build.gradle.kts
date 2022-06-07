
plugins {
    scala
    application
    `java-library`
    id("org.openjfx.javafxplugin") version "0.0.13"
}

version = "1.0"

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                "Implementation-Version" to project.version))
    }
    project.setProperty("archivesBaseName", "towerdefense")
}



repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.6")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.scalafx:scalafx_2.13:18.0.1-R27")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")

}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnit("4.13.2")

            dependencies {
                implementation("org.scalatest:scalatest_3:3.2.11")
                implementation("org.scalatestplus:junit-4-13_3:3.2.12.0")
                runtimeOnly("org.scala-lang.modules:scala-xml_2.13:1.2.0")
            }
        }
    }
}

javafx {
    version = "17.0.1"
    modules("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.media", "javafx.swing", "javafx.web")
}

application {
    mainClass.set("View.GameLauncher")
}
