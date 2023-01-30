import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    kotlin("multiplatform")
}

plugins.withId(pluginId("kotlin-multiplatform")) {
    the<KotlinMultiplatformExtension>().apply {
        js() {
            browser()
        }
    }
}
