buildscript {
    dependencies {
        classpath(libs.kotlinx.serialization.json)
        classpath(libs.gradle)
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.perf.plugin)
        classpath(libs.firebase.appdistribution.gradle)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version ("2.0.1") apply false
    alias(libs.plugins.serialization.json) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}