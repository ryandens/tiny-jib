buildscript {
    dependencies {
        classpath("com.google.cloud.tools:jib-ownership-extension-gradle:0.1.0")
    }
}

plugins {
    java
    id("tel.schich.tinyjib")
}

repositories {
    mavenCentral()
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
            this.extensionClass = "com.google.cloud.tools.jib.gradle.extension.ownership.JibOwnershipExtension"
            this.extraConfiguration.set(Action<com.google.cloud.tools.jib.gradle.extension.ownership.Configuration> {
                rules {
                    rule {
                        glob = "/app/classes/**"
                        ownership = "300"
                    }
                }
            })
        }
    }

}