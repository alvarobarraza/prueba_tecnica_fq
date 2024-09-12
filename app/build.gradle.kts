plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.technical_test_fq"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.technical_test_fq"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //viewModel and liveData
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")


    // Room
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.0")

    //lottie
    implementation ("com.airbnb.android:lottie:4.2.0")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //utils
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.13.2")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("com.auth0:java-jwt:4.2.1")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

kapt {
    correctErrorTypes = true
}
