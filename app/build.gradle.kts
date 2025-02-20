plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mydemo"
    compileSdk = 35

    defaultConfig {
        ndk {
            abiFilters.addAll(setOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
        }
        applicationId = "com.example.mydemo"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    flavorDimensions += "whitelabel"
    productFlavors {
        create("groen") {
            dimension = "whitelabel"
            applicationIdSuffix = ".groen"
            versionNameSuffix = "-groen"
        }
        create("rood") {
            dimension = "whitelabel"
            applicationIdSuffix = ".rood"
            versionNameSuffix = "-rood"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.coil.network.okhttp)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}