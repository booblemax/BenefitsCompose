plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {

    compileSdk = Apps.compileSdk
    buildToolsVersion = Apps.buildToolVersion

    defaultConfig {
        applicationId = Apps.appId
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.incremental", "true")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")     }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true

            buildConfigField("String", "USERNAME", "\"maksim.astapenko@mbicycle.com\"")
            buildConfigField("String", "PASSWORD", "\"qwer1234\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.lifecycleKtx)
    implementation(Libs.coil)
    implementation(Libs.timber)
    implementation(Libs.gson)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.uiToolkit)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.activityCompose)
    implementation(Libs.Compose.navigation)

    implementation(Libs.Koin.koin)
    implementation(Libs.Koin.koinCompose)

    implementation(Libs.Accompanist.placeholder)
    implementation(Libs.Accompanist.systemUiController)

    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.compiler)

    implementation(Libs.Firebase.databaseKtx)
    implementation(Libs.Firebase.authKtx)
    implementation(Libs.Firebase.storageKtx)
    implementation(Libs.Firebase.uiStorage)

    implementation(project(mapOf("path" to ":shared")))
}