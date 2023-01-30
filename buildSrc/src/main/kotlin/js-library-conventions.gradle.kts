import gradle.kotlin.dsl.accessors._b7dceff322b6b2884f430c7bbb2ba83b.kotlin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
   kotlin("multiplatform")
}

plugins.withId(pluginId("kotlin-multiplatform")) {
   the<KotlinMultiplatformExtension>().apply {
      js() {
         browser()
      }
   }
}
