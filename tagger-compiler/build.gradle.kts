import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    // KSP
    implementation(libs.ksp.api)

    // Kotlin
    implementation(libs.kotlin)

    // Code generation
    implementation(libs.kotlinpoet)
    implementation(libs.kotlinpoet.ksp)

    // Project dependency
    implementation(project(":tagger"))
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
