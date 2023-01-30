@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.kfc.application)
    id(libs.plugins.kfc.react)
    id(libs.plugins.dgg)
}

kotlin.js {
    browser()
    binaries.executable()
}

dependencies {
    implementation(project(":shared:core"))


    implementation(libs.kotlin.wrappers.browser)
    implementation(libs.kotlin.wrappers.react)
    implementation(libs.kotlin.wrappers.react.dom)
    implementation(libs.kotlin.wrappers.react.router.dom)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
}
