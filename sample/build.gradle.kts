import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp.plugin)
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
}

//kapt {
//    arguments {
//        arg("tagger.package_name", "com.anthonycr.sample")
//    }
//}

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
    implementation(libs.appcompat.v7)
    implementation(libs.constraint.layout)

    implementation(project(":tagger"))
    ksp(project(":tagger-compiler"))
}
