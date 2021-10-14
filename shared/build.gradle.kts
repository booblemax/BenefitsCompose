import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

sqldelight {
    database("Benefits") {
        packageName = "by.akella.sqldelight.benefits"
        sourceFolders = listOf("sqldelight")
    }
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
//        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "shared"
        }
        // set path to your ios project podfile, e.g. podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.kotlin_stdlib)
                implementation(Libs.SQLDelight.runtime)
                implementation(Libs.coroutines)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Libs.SQLDelight.android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Libs.SQLDelight.ios)
            }
        }
    }
}

android {
    compileSdk = Apps.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
    }
    packagingOptions {
        resources.excludes.add("META-INF/*.kotlin_module")
    }
}