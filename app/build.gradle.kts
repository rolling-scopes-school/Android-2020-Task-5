@file:Suppress("LocalVariableName")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ng_designs.android_2021_task_5"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (
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
        viewBinding = true
    }

    kapt {
        useBuildCache = false
        correctErrorTypes = true
    }
}

dependencies {

    val paging_version = "3.0.1"
    val retrofit_version = "2.9.0"
    val okhttp_logging_version = "4.9.0"
    val coroutine_version = "1.4.1"
    val coil_version = "1.3.2"
    val moshi_version = "1.12.0"
    val nav_version = "2.3.5"

    // User implementations
    implementation ("io.coil-kt:coil:$coil_version")
    implementation ("io.coil-kt:coil-gif:$coil_version")

    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttp_logging_version")

    implementation ("com.squareup.moshi:moshi-kotlin:$moshi_version")
//    kapt ("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")

    implementation ("androidx.paging:paging-runtime-ktx:$paging_version")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")

    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    androidTestImplementation ("androidx.navigation:navigation-testing:$nav_version")

    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")

    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.0")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:2.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.5.30")


    // Android Studio implementations
    implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}
