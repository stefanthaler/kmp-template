import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
}

plugins.withId(pluginId("kotlin-multiplatform")) {
    the<KotlinMultiplatformExtension>().apply {
        android()
    }
}

android {
    compileSdk = catalog.intVersion("android-compile-sdk")

    defaultConfig {
        minSdk = catalog.intVersion("android-min-sdk")
        targetSdk = catalog.intVersion("android-target-sdk")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(catalog.version("jvm-target"))
        targetCompatibility = JavaVersion.toVersion(catalog.version("jvm-target"))
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDir("src/androidMain/kotlin")
            res.srcDir("src/androidMain/res")
        }
    }
}
