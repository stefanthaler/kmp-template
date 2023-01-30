rootProject.name = "kmp-templates"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        google() // android specific stuff
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }

    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

// shared
include(":shared:core")

// apps
include(":apps:android:app")
include(":apps:js:app")


include(":apps:ios:facade")
include(":apps:ios:dependency-cocoapods")
