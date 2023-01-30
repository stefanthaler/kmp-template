import org.gradle.api.Project

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
