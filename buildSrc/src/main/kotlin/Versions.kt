object Apps {
    const val appId = "by.akella.benefits"

    const val compileSdk = 31
    const val buildToolVersion = "30.0.3"
    const val minSdk = 23
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Classpath {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val ktlinGradlePlugin = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlintGradlePlugin}"
    const val testInstrumentalRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val sqldelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
}
object Versions {
    const val ktLint = "6.1.0"
    const val ktlintGradlePlugin = "9.2.1"
    const val detekt = "1.10.0"
    const val sqldelight = "1.5.1"

    const val googleServices = "4.3.10"
    const val gradle = "7.0.3"
    const val kotlin = "1.5.30"
    const val appcompat = "1.3.1"
    const val ktx = "1.6.0"
    const val material = "1.4.0"
    const val compose = "1.0.3"
    const val compose_activity = "1.3.1"
    const val navigation = "2.4.0-alpha06"
    const val lifecycleKtx = "2.4.0-rc01"
    const val koin = "3.1.2"
    const val coil = "1.3.2"
    const val accompanist = "0.18.0"
    const val firebaseBom = "28.4.0"
    const val firebaseDatabase = "20.0.2"
    const val firebaseStorage = "20.0.0"
    const val firebaseAuth = "21.0.1"
    const val firebaseUi = "7.2.0"
    const val timber = "4.7.1"
    const val room = "2.3.0"
    const val gson = "2.8.7"
    const val conscrypt = "2.4.0"
    const val threetenabp = "1.2.3"
    const val coroutines = "1.5.2"
    const val coroutinesNative = "1.5.2-native-mt"
    const val di = "1.0.4.5"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCoreNative = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesNative}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val di = "io.github.anioutkazharkova:di-multiplatform-lib:${Versions.di}"

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val uiToolkit = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.compose_activity}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    }

    object Koin {
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Accompanist {
        const val placeholder = "com.google.accompanist:accompanist-placeholder:${Versions.accompanist}"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Firebase {
        const val databaseKtx = "com.google.firebase:firebase-database-ktx:${Versions.firebaseDatabase}"
        const val authKtx = "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuth}"
        const val storageKtx = "com.google.firebase:firebase-storage-ktx:${Versions.firebaseStorage}"
        const val uiStorage = "com.firebaseui:firebase-ui-storage:${Versions.firebaseUi}"
    }

    object SQLDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqldelight}"
        const val android = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
        const val ios = "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
    }
}