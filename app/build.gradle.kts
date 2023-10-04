plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.roomtest"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.roomtest"
        minSdk = 29
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.github.dhaval2404:imagepicker:2.1")
    // Fragment
    implementation("androidx.fragment:fragment-ktx:1.4.0")
    // Activity
    implementation("androidx.activity:activity-ktx:1.4.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    //Room
    implementation ("androidx.room:room-ktx:2.4.0")
    ksp( "androidx.room:room-compiler:2.4.0")
    //Lottie
    implementation("com.airbnb.android:lottie:3.4.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    ksp("com.github.bumptech.glide:compiler:4.16.0")

}