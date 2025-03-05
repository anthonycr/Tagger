import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    // Annotation processing
    val autoServiceVersion = "1.0-rc3"
    implementation("com.google.auto.service:auto-service:$autoServiceVersion")
    kapt("com.google.auto.service:auto-service:$autoServiceVersion")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")

    // Code generation
    implementation("com.squareup:kotlinpoet:0.6.0")

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
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
