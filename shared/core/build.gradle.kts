@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.kotlin.multiplatform)

    // configure targets
    `android-library-conventions`
    `ios-internal-library-conventions`
    `js-library-conventions`


    //creates source sets for configured targets
    `source-set-conventions`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val mobileCommonMain by getting {

        }

        val androidMain by getting

        val jsMain by getting {
            dependencies {
                wrappersBom()
                implementation(libs.kotlin.wrappers.browser)
                implementation(libs.kotlin.wrappers.csstype)
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val iosMain by getting
    }
}

android {
    namespace = "net.thalerit.kmp.template.shared.core"
}
