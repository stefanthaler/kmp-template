import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

// gradle properties helper
fun Project.version(target: String): String =
    prop("$target.version")

fun Project.intProp(
    name: String
): Int {
    val p = prop(name)
    return p.toIntOrNull()
        ?: throw IllegalArgumentException("'$name' not an integer, was '$p'")
}

fun Project.prop(name: String): String =
    findProperty(name) as String?
        ?: throw IllegalArgumentException("'$name' not found, please add to `gradle.properties`")

fun Project.configureKotlinMultiplatformExtension(
    configure: KotlinMultiplatformExtension.() -> Unit,
): Unit {
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        the<KotlinMultiplatformExtension>().apply(configure)
    }
}
