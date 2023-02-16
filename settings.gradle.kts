pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()

        maven(uri("https://plugins.gradle.org/m2/")) // For kotlinter-gradle
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()


        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "ComposeMultiplatformApp"
include(":androidApp")
include(":shared")