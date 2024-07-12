plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization.json)
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("com.google.firebase.appdistribution")
    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.thezayin.lpgadmin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.thezayin.lpgadmin"
        minSdk = 24
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
                "proguard-rules.pro"
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
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":entities"))
    implementation(project(":framework"))
    implementation(project(":analytics"))
    implementation(project(":common"))
    implementation(project(":di"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":addproducts"))
    implementation(project(":home"))
    implementation(project(":orders"))
    implementation(project(":products"))
    implementation(project(":productdetails"))



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //viewmodel and livedata
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.common.java8)

//neu morphic
    implementation(libs.neumorphic)


    //koin dependency injection
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
    testImplementation(libs.koin.test.junit4)
    implementation(libs.koin.androidx.navigation)

    //serialization and ktor
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialize)

    //Custom Navigation
    implementation(libs.raamcosta.destination.core)
    ksp(libs.raamcosta.destination.ksp)

    //lottie anim
    implementation(libs.lottie.compose)
    implementation(libs.androidx.animation)
    implementation(libs.accompanist.navigation.animation)

    //firebase
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.inappmessaging.display)
    implementation(libs.firebase.config)
    implementation(libs.jsoup.jsoup)

    implementation(libs.timber)
    implementation(libs.androidx.ui.graphics.android)

    implementation(libs.coil.gif)
    implementation(libs.coil.compose)

    //firebase
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.appcheck.debug)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.inappmessaging.display)
    implementation(libs.firebase.perf)

    //room database
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)

    //Google Services & Maps
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.maps.android:maps-compose:2.9.0")
    implementation("com.google.android.gms:play-services-maps:19.0.0")

    //Accompanist (Permission)
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
}