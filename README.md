# Template KMP client technologies

This is a biased Kotlin Multi Platform project template that has an Android, iOS and a JS client.

# TODO

* Setup tests
* Setup code obfuscation / optimization
* JVM client
* Native client

# Design choices
**Gradle**: 
* `Kotlin DSL` 
* `buildSrc` for custom plugins
* Version catalogs

**Shared code**
* Kotlin IR
* Kotlin Multi Platform with hierarchical setup: common, mobileCommon

**Android**:
* Kotlin, Compose 
* Dependency management: Gradle
* Build Types, product flavors 

**iOS**:
* Kotlin, Swift, SwiftUI
* Dependency management: cocoapods

**JS**:
* KotlinJS + React Wrappers
* npm 

**IDE** 
* Idea 
* XCode 

# Folder structure

- `settings.gradle.kts`: manage repositories + subprojects
- `build.gradle.kts`: root build gradle file, initialize plugins that are not initialized in `buildSrc`
- `gradle.properties`: gradle properties for projects
- `local.properties`: properties specific to the [Android Gradle plugin]()https://developer.android.com/studio/build#properties-files.
- `libs.versions.toml`: define all libraries and plugins, see [Version Catalog](https://docs.gradle.org/current/userguide/platforms.html)
- `buildSrc/`: conventions plugins + build helpers see [buildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)
- `shared/`: Kotlin multi platform [KMP](https://kotlinlang.org/docs/multiplatform.html) modules
- `apps/`: Client applications + modules specific to target platform

# Setup

## Build & Run

* **Android:** `./gradlew apps:android:app installDebug`
* **iOS:** open `apps/ios/app/iosApp.xcworkspace` with xcode and run
* **JS:** `./gradlew apps:js:app:browserDevelopmentRun`

## Visualize module dependencies
* build dependency graph: `./gradlew apps:android:app:generateProjectDependencyGraph` [docs](https://github.com/vanniktech/gradle-dependency-graph-generator-plugin)

## Update library versions

* run: `./gradlew versionCatalogUpdate`
* configure task in: `ROOT/build.gradle.kts`
* [Plugin documentation](https://github.com/littlerobots/version-catalog-update-plugin)

# Known issues: 
* Resolution of the configuration :detachedConfiguration1: TODO link issue 
* Configuration 'jsNpm' was resolved during configuration time.: TODO link issue
