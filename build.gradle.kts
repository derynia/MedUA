import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.hilt)
        classpath(BuildPlugins.benManesVersionsPlugin)
        classpath(BuildPlugins.ktlint)
    }
}

allprojects {
    apply(plugin = BuildPlugins.ktlintMain)
}

apply(plugin = BuildPlugins.benManesVersionsPluginMain)

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    versionsOptions()
}

fun DependencyUpdatesTask.versionsOptions() {
    rejectVersionIf {
        isAlpha(candidate.version) || isMilestone(candidate.version) || isNonStable(candidate.version)
    }
    gradleReleaseChannel = VersionsTest.GRADLE_RELEASE_CHANNEL
    checkConstraints = false
    checkForGradleUpdate = true
    outputFormatter = VersionsTest.OUTPUT_FORMATTER
    outputDir = VersionsTest.OUTPUT_DIR
    reportfileName = VersionsTest.REPORT_FILE_NAME
}

@Suppress("unused")
fun isNonStable(version: String): Boolean {
    val regex = VersionsTest.REGEX.toRegex()
    val stableKeyword = VersionsTest.stableKeyword.any { version.toUpperCase().contains(it) }
    val nonStableKeyword = VersionsTest.nonStableKeyword.any { version.toUpperCase().contains(it) }
    val isStable = !nonStableKeyword && (stableKeyword || regex.matches(version))
    return isStable.not()
}

fun isAlpha(version: String) =
    VersionsTest.alphaKeyword.any { version.toLowerCase().contains(it) }

fun isMilestone(version: String) =
    VersionsTest.milestoneKeyword.any { version.toUpperCase().contains(it) }