import nl.littlerobots.vcu.plugin.versionCatalogUpdate

subprojects {
    apply(plugin = "kotlin-conventions")
}

// if plugins are not used in buildSrc,  (e.g., kotlin) they need to be initialized here
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.manes)
    alias(libs.plugins.vcu)
    alias(libs.plugins.dgg) apply false
}

tasks.wrapper {
    gradleVersion = "7.6"
}

versionCatalogUpdate {
    sortByKey.set(false)

    keep {
        keepUnusedVersions.set(true)
        keepUnusedLibraries.set(true)
        keepUnusedPlugins.set(true)
    }
}
