plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion
    namespace = "com.medua"

    defaultConfig {
        applicationId = "com.medua"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion  = "1.3.2"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Deps.coreKtx)

    // Compose
    implementation(Deps.activityCompose)
    implementation(Deps.composeUi)
    implementation(Deps.composePreview)
    implementation(Deps.material)
    implementation(Deps.composeNav)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.extJunit)
    androidTestImplementation(Deps.espresso)
    androidTestImplementation(Deps.composeTest)
    debugImplementation(Deps.composeDebug)
    debugImplementation(Deps.composeTestManifest)

    /*Logs*/
    implementation(Deps.timber)

    // Hilt
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)
}