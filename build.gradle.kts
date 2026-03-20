import org.jetbrains.kotlin.buildtools.api.ExperimentalBuildToolsApi
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import pl.allegro.tech.build.axion.release.domain.PredefinedVersionCreator

plugins {
  `java-gradle-plugin`
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.tapmoc)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.pluginPublish)
  alias(libs.plugins.axionRelease)
  alias(libs.plugins.detekt)
}

tapmoc {
  gradle("8.0.0")
}

extensions.getByType(KotlinJvmExtension::class.java).apply {
  @OptIn(ExperimentalBuildToolsApi::class, ExperimentalKotlinGradlePluginApi::class)
  compilerVersion.set(libs.versions.kotlin.compiler.get())
}

scmVersion {
  tag {
    prefix = "v"
  }
  nextVersion {
    suffix = "SNAPSHOT"
    separator = "-"
  }
  versionCreator = PredefinedVersionCreator.SIMPLE.versionCreator
}

group = "tel.schich.tinyjib"
version = scmVersion.version

repositories {
  mavenCentral()
  gradlePluginPortal()
}

dependencies {
  implementation(libs.serialization.json)
  implementation(libs.jibCore)
  implementation(libs.guava)
  compileOnly(libs.gradle.api)
}

gradlePlugin {
  displayName
  website = "https://github.com/pschichtel/tiny-jib"
  vcsUrl = "https://github.com/pschichtel/tiny-jib"
  plugins {
    create("tinyJibPlugin") {
      id = "tel.schich.tinyjib"
      implementationClass = "tel.schich.tinyjib.TinyJibPlugin"
      displayName = "Tiny Jib Gradle Plugin"
      description = "A heavily simplified version of Google's Jib plugin"
      tags = listOf("container", "jib")
    }
  }
}

tasks.jar {
  manifest {
    attributes("Implementation-Version" to version.toString())
  }
}

tasks.check {
  dependsOn(tasks.detektMain, tasks.detektTest)
}

detekt {
  parallel = true
  buildUponDefaultConfig = true
  config.setFrom(files(project.rootDir.resolve("detekt.yml")))
}
