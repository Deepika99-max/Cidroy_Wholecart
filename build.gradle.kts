plugins {
    java
}

group = "org.example.wholecart"
version = "1.0.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
dependencies {

    implementation("org.seleniumhq.selenium:selenium-java:4.35.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
    implementation("commons-io:commons-io:2.20.0")
    implementation("org.testng:testng:7.11.0")
    implementation("org.slf4j:slf4j-simple:2.0.17")
    testImplementation("io.rest-assured:rest-assured:5.5.6")
    implementation("com.aventstack:extentreports:5.1.2")
    implementation("io.github.cdimascio:dotenv-java:3.2.0")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng.xml")
    }

    systemProperty(
        "browser",
        System.getProperty("browser", "chrome")
    )

    systemProperty(
        "headless",
        System.getProperty("headless", "false")
    )

    systemProperty(
        "environment",
        System.getProperty("environment", "qa")
    )

    testLogging {
        events(
            "passed",
            "skipped",
            "failed"
        )

        exceptionFormat =
            org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}