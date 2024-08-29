rootProject.name = "book-catalog"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("gradle-plugins")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("book-catalog-api")
include("book-catalog-app")
include("book-catalog-client")
