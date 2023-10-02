@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    listOf(
        libs.androidx.test.ext.junit,
        libs.espresso.core,
        platform(libs.compose.bom),
        libs.ui.test.junit4,
    ).forEach(::androidTestImplementation)

    listOf(
        libs.ui.tooling,
        libs.ui.test.manifest,
    ).forEach(::debugImplementation)

    listOf(
        libs.core.ktx,
        libs.lifecycle.runtime.ktx,
        libs.activity.compose,
        libs.compose.constraintLayout,
        platform(libs.compose.bom),
        libs.ui,
        libs.ui.graphics,
        libs.ui.tooling.preview,
        libs.material3,
    ).forEach(::implementation)

    listOf(
        libs.junit,
    ).forEach(::testImplementation)
}
