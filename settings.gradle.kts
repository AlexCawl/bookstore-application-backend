pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "bookstore-app-backend"
include(":application")
include(":feature")
include(":core:api")
include(":core:configuration")
include(":core:db")
