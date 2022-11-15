object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val hilt by lazy {"com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"}
    val benManesVersionsPlugin by lazy {"com.github.ben-manes:gradle-versions-plugin:${Versions.benManesVersionsPlugin}"}
    val benManesVersionsPluginMain by lazy { "com.github.ben-manes.versions" }
    val ktlint by lazy {"org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"}
    val ktlintMain by lazy { "org.jlleitschuh.gradle.ktlint" }
}

@Suppress("MemberVisibilityCanBePrivate")
object VersionsTest {
    const val GRADLE_RELEASE_CHANNEL = "release-candidate"
    const val OUTPUT_FORMATTER = "plain,json,xml,html"
    const val OUTPUT_DIR = "build/reports/dependency-updates"
    const val REPORT_FILE_NAME = "advice"

    const val REGEX = "^[0-9,.v-]+(-r)?$"
    val alphaKeyword = listOf(
        "alpha",
    )
    val milestoneKeyword = listOf(
        "M1",
        "M2",
    )
    val stableKeyword = listOf(
        "RELEASE",
        "FINAL",
        "GA",
    )
    val nonStableKeyword = listOf("-rc", "-beta", "-Beta") + milestoneKeyword
}

/**
 * To define dependencies
 */
object Deps {
    // Core
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.ktx}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }

    // Logging
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }

    // Test
    val extJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }

    // Hilt
    val hilt by lazy {"com.google.dagger:hilt-android:${Versions.hilt}"}
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    // Compose
    val activityCompose by lazy {"androidx.activity:activity-compose:${Versions.activityComposeVersion}"}
    val composeUi by lazy {"androidx.compose.ui:ui:${Versions.composeVersion}"}
    val composePreview by lazy {"androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"}
    val material3 by lazy {"androidx.compose.material3:material3:${Versions.material3Version}"}
    val material by lazy {"androidx.compose.material:material:${Versions.materialVersion}"}
    val composeNav by lazy {"androidx.navigation:navigation-compose:${Versions.navigation}"}

    val composeTest by lazy {"androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"}
    val composeDebug by lazy {"androidx.compose.ui:ui-tooling:${Versions.composeVersion}"}
    val composeTestManifest by lazy {"androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}"}

}