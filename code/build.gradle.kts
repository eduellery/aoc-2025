import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    application
    jacoco
    id("com.diffplug.spotless") version "8.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

jacoco {
    toolVersion = "0.8.14"
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:deprecation")
}

tasks.named<Test>("test") {
    testClassesDirs = files(testClassesDirs.files.sorted())

    useJUnitPlatform()

    testLogging {
        events(PASSED, SKIPPED, FAILED, STANDARD_OUT, STANDARD_ERROR)
    }

    addTestListener(object : TestListener {
        override fun afterTest(desc: TestDescriptor, result: TestResult) {
            println(
                "Test ${desc.className}.${desc.displayName} " +
                    "took ${result.endTime - result.startTime} ms"
            )
        }

        override fun beforeTest(desc: TestDescriptor) {}
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
    })
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
        html.required.set(true)
    }
}

spotless {
    java {
        target("src/**/*.java")

        googleJavaFormat("1.32.0")
            .reflowLongStrings()
            .groupArtifact("com.google.googlejavaformat:google-java-format")
            .aosp()

        removeUnusedImports()
        importOrder("java", "javax", "org", "com", "")

        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("src/**/*.kt")
        ktlint("1.3.1")
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("misc") {
        target("*.md", ".gitignore", ".editorconfig")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
