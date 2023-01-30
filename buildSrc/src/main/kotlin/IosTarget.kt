import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

// https://kotlinlang.org/docs/multiplatform-mobile-ios-dependencies.html#workaround-to-enable-ide-support-for-the-shared-ios-source-set
fun KotlinMultiplatformExtension.iosTarget(
    configure: KotlinNativeTarget.() -> Unit = { }
) {
    iosX64(configure)
    iosArm64(configure)
    iosSimulatorArm64(configure)
}
