import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.internal.os.OperatingSystem

plugins {
    scala
    application
    `java-library`
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.glovoapp.semantic-versioning") version "1.1.8"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.scoverage") version "5.0.0"
    id("com.diffplug.spotless") version "6.8.0"
}


version = project.version

tasks.jar {
    manifest {
        attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
                "Main-Class" to "View.GameLauncher")
        )
    }
    project.setProperty("archivesBaseName", "towerdefense")
}

tasks {
    named<ShadowJar>("shadowJar") {
        if (OperatingSystem.current().isWindows) {
            archiveBaseName.set("towerdefense-windows")
        } else if(OperatingSystem.current().isLinux) {
            archiveBaseName.set("towerdefense-unix")
        } else if(OperatingSystem.current().isMacOsX) {
            archiveBaseName.set("towerdefense-mac")
        } else {
            archiveBaseName.set("towerdefense-others")
        }
        manifest {
            attributes(mapOf("Main-Class" to "View.GameLauncher"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.6")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.scalafx:scalafx_2.13:18.0.1-R27")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("com.google.code.gson:gson:2.9.0")
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


task("printVersion") {
    doLast {
        println("The project current version is ${project.semanticVersion.version.get()}")
    }
}

task("getVersion") {
    doLast {
        println(project.version)
    }
}

tasks.withType<ScalaCompile>().configureEach {
    scalaCompileOptions.apply {
        additionalParameters = listOf("-Werror")
    }
}

tasks.compileScala.configure {
    dependsOn(tasks.getByName("spotlessCheck"))
}

application {
    mainClass.set("View.GameLauncher")
}
