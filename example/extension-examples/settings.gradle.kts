rootProject.name = "extension-examples"

include(":jib-ownership")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    includeBuild("../..")
}
