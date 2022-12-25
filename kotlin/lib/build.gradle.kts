import org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    val kotlinVersion = "1.7.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.9.1")
                targets {
                    all {
                        testTask.configure {
                            testLogging {
                                events = setOf(FAILED)
                                exceptionFormat = FULL
                                showStackTraces = false

                            }
                        }
                    }
                }
        }                

        dependencies {
            fun kotest(artifact: String): Dependency? {
                val version = "5.5.4"
                return implementation("io.kotest:$artifact:$version")
            }
            kotest("kotest-assertions-core-jvm")
            kotest("kotest-runner-junit5")
            kotest("kotest-framework-datatest")
        }
    }
}
