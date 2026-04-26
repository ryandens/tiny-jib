rootProject.name = "extension-examples"

include(":javaagent")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    includeBuild("../..")
}
