@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.kotlin.multiplatform)
    id(libs.plugins.dgg)

    `ios-external-library-conventions`
    `source-set-conventions`
}

// umbrella framework to integrate all shared libraries, because integrating multiple dependencies creates linking issues
// https://youtrack.jetbrains.com/issue/KT-42247
// https://kotlinlang.org/docs/multiplatform-build-native-binaries.html

//https://kotlinlang.org/docs/multiplatform-mobile-ios-dependencies.html#workaround-to-enable-ide-support-for-the-shared-ios-source-set
kotlin {
    cocoapods {
        podfile = project.file("../../ios/app/Podfile")

        summary = "Facade module"
        name = "kmp" // name of pod

        framework {
            baseName = "kmp" // name of package in ios-app

            // export internal project dependencies
            // example: export(project(":shared:core"))
            // needs to be exported if it should be used in ios-app
            //export(project(":apps:ios:dependency-cocoapods"))
        }

        // add pod dependencies here
        // TODO remove pod dependency after https://youtrack.jetbrains.com/issue/KT-56191 is resolved
        //pod("AFNetworking", libs.versions.ios.pods.afnetworking.get())
    }

    // shortcut for exporting kmp projects
    expose(project(":apps:ios:facade"))
    expose(project(":shared:core"))

    // configure dependencies here
    sourceSets {
        val iosMain by getting {
            dependencies {
                // add dependencies as api dependencies
                // example: api(project(":shared:core"))
            }
        }
    }
}
