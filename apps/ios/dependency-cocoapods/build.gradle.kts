@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `ios-external-library-conventions`
}

//https://kotlinlang.org/docs/multiplatform-mobile-ios-dependencies.html#with-cocoapods
kotlin {
    iosTarget()

    createHierarchicalSourcesets()

    cocoapods {
        pod("AFNetworking", libs.versions.ios.pods.afnetworking.get())
    }
}
