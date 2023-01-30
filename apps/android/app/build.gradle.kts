@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.android.application)
    id(libs.plugins.kotlin.android)
    id(libs.plugins.kotlin.kapt)
    id(libs.plugins.dgg)
}

// https://developer.android.com/studio/build/dependencies#dependency_configurations
// available: implementation, api, compileOnly, runtimeOnly, annotationProcessor, lintChecks, lintPublish, apk, compile, provide
val freeDebugImplementation by configurations.creating

//merging manifests https://developer.android.com/studio/build/manage-manifests
android {
    compileSdk = libs.versions.android.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.android.app.id.get()

        minSdk = libs.versions.android.min.sdk.get().toInt()
        targetSdk = libs.versions.android.target.sdk.get().toInt()
        versionCode = libs.versions.android.app.code.get().toInt()
        versionName = libs.versions.android.app.name.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        //
        manifestPlaceholders["hostName"] = "www.example.com"

        // A library dependency includes a flavor dimension that your app does not.
        missingDimensionStrategy("free", "paid")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.target.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.target.get())
    }

    //https://developer.android.com/studio/build/build-variants#build-types
    buildTypes {
        release {
//            minifyEnabled =  false
//            proguardFiles = getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        }

        getByName("debug") { // could also be `debug {`
            //TODO get from local properties
            manifestPlaceholders["hostName"] = "debug.example.com"
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }

        create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".debugStaging"
            manifestPlaceholders["hostName"] = "internal.example.com"
            // https://developer.android.com/studio/build/build-variants#resolve_matching_errors
            // Your app includes a build type that a library dependency does not.
            matchingFallbacks += listOf("debug", "release")
        }
    }

    //https://developer.android.com/studio/build/build-variants#build-types
    flavorDimensions += "version"
    productFlavors {
        create("free") {
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"

            // For a given flavor dimension that exists in both the app and its library dependency, your app includes flavors that the library does not.
            matchingFallbacks += listOf("demo", "trial")
        }
        create("paid") {
            dimension = "version"
            applicationIdSuffix = ".paid"
            versionNameSuffix = "-paid"
        }
    }

    // https://developer.android.com/studio/build/gradle-tips#change-default-source-set-configurations
    sourceSets {
        getByName("main") {
//            kotlin.setSrcDirs(listOf("other/kotlin"))
//            java.setSrcDirs(listOf("other/java"))
//            res.setSrcDirs(listOf("other/res1", "other/res2"))
            manifest.srcFile("src/main/AndroidManifest.xml")
        }

        getByName("debug") {

        }

        getByName("staging") {

        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "net.thalerit.kmp.template"
}


dependencies {
    implementation(project(":shared:core"))

    val composeBom = project.dependencies.enforcedPlatform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)


    // https://developer.android.com/studio/build/gradle-tips#target-specific-builds-with-dependency-configurations
    "debugImplementation"(libs.junit)
    "freeImplementation"(libs.junit)
    freeDebugImplementation(libs.junit)
}
