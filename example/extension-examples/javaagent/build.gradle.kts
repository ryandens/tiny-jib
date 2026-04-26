buildscript {
    dependencies {
        classpath("com.google.cloud.tools.jib:com.google.cloud.tools.jib.gradle.plugin:3.5.3")
    }
}

plugins {
    java
    id("tel.schich.tinyjib")
    id("com.ryandens.javaagent-jib") version "0.12.0"
}

repositories {
    mavenCentral()
}

dependencies {
    javaagent("io.opentelemetry.javaagent:opentelemetry-javaagent:1.11.1")
}

tinyJib {
    from {
        image = "docker.io/library/eclipse-temurin:17-alpine"
    }
    container {
        mainClass = "tel.schich.tinyjib.example.moda.Main"
    }
    to {
        image = "ghcr.io/pschichtel/tinyjib/example/moda:latest"
    }
    pluginExtensions {
        extension {
            this.extensionClass = "com.ryandens.javaagent.JavaagentJibExtension"
        }
    }
}