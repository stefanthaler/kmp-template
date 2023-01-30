import gradle.kotlin.dsl.accessors._b7dceff322b6b2884f430c7bbb2ba83b.cocoapods
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

// shortcut for facade project
fun KotlinMultiplatformExtension.expose(
    other: Project
) {
    cocoapods.framework {
        export(other)
    }
    val otherPods = other.the<KotlinMultiplatformExtension>().cocoapods.pods
    cocoapods.pods.addAll(otherPods)
    sourceSets.getByName("iosMain").dependencies {
        api(other)
    }
}

// TODO add test sourcesets
internal fun KotlinMultiplatformExtension.createHierarchicalSourcesets() {
    val commonMain = sourceSets.getByName("commonMain")


    if (hasJsTarget) {
        sourceSets.getByName("jsMain").dependsOn(commonMain)
    }

    if (!hasMobileTarget) {
        return
    }

    val mobileCommonMain = sourceSets.create("mobileCommonMain")
    mobileCommonMain.dependsOn(commonMain)

    if (hasIosTarget) {
        iosSourceSets(mobileCommonMain)
    }

    if (hasAndroidTarget) {
        sourceSets.getByName("androidMain").dependsOn(mobileCommonMain)
    }
}

private fun KotlinMultiplatformExtension.iosSourceSets(
    parentSourceSet: KotlinSourceSet? = null
) {
    val parent = parentSourceSet ?: sourceSets.getByName("commonMain")
    val iosX64Main = sourceSets.getByName("iosX64Main")
    val iosArm64Main = sourceSets.getByName("iosArm64Main")
    val iosSimulatorArm64Main = sourceSets.getByName("iosSimulatorArm64Main")
    val iosMain = sourceSets.create("iosMain")

    iosMain.dependsOn(parent)

    iosX64Main.dependsOn(iosMain)
    iosArm64Main.dependsOn(iosMain)
    iosSimulatorArm64Main.dependsOn(iosMain)
}

private val KotlinMultiplatformExtension.hasJsTarget: Boolean
    get() = targets.findByName("js") != null

private val KotlinMultiplatformExtension.hasIosTarget: Boolean
    get() = targets.names.any { "ios" in it }

private val KotlinMultiplatformExtension.hasAndroidTarget: Boolean
    get() = targets.findByName("android") != null

private val KotlinMultiplatformExtension.hasMobileTarget: Boolean
    get() = hasAndroidTarget || hasIosTarget
