import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins.withId(pluginId("kotlin-multiplatform")) {
    the<KotlinMultiplatformExtension>().apply {
        createHierarchicalSourcesets()
    }
}
