import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.wrappersBom() {
    val bomStr = project.catalog.findLibrary("kotlin-wrappers-bom").get().get().toString()
    implementation(enforcedPlatform(bomStr))
}
