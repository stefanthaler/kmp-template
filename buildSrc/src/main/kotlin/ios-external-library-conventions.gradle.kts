@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}

// override in library
version = "1.0.0-override-in-library"
group = "net.thalerit"

kotlin {
    cocoapods {
        summary = "KMP library: ${project.name}"
        homepage = catalog.version("ios-homepage")
        ios.deploymentTarget = catalog.version("ios-deployment-target")
        framework {
            isStatic = false
            baseName = project.name
        }
    }

    iosTarget()
}
