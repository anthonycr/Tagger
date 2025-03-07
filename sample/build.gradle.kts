import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 35
    buildToolsVersion = "35.0.0"

    defaultConfig {
        namespace = "com.anthonycr.tagger.sample"
        applicationId = "com.anthonycr.tagger.sample"
        minSdk = 19
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

//    sourceSets {
//        getByName("main") {
//            java.srcDirs(
//                "build/generated/source/kapt/debug",
//                "build/generated/source/kapt/release",
//                "build/generated/source/kaptKotlin/debug",
//                "build/generated/source/kaptKotlin/release"
//            )
//        }
//    }
}

kapt {
    arguments {
        arg("tagger.package_name", "com.anthonycr.sample")
    }
}

java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        jvmTarget.set(JvmTarget.JVM_17)
    }
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get().toInt()))
    }
}

dependencies {
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.android.support.constraint:constraint-layout:2.0.4")

    implementation(project(":tagger"))
    kapt(project(":tagger-compiler"))
}
