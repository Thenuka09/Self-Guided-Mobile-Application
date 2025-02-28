plugins {
    id("com.android.application")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.smile_v1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.smile_v1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    //noinspection GradleCompatible
    implementation("androidx.appcompat:appcompat:1.7.0")

    //implementation ("androidx.exifinterface:exifinterface:1.3.3")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("com.google.android.material:material:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // to use the API endpoints
    implementation ("com.android.volley:volley:1.2.1")
    // Toasty library
    implementation ("com.github.GrenderG:Toasty:1.5.2")
    // firebase
    //noinspection BomWithoutPlatform
    implementation ("com.google.firebase:firebase-bom:33.5.1")
    // realtime database
    //implementation ("com.google.firebase:firebase-database")

    implementation ("com.google.firebase:firebase-database:21.0.0")

    // fancy alert dialog
    implementation ("com.github.Shashank02051997:FancyGifDialog-Android:1.5")

    //showing gif images
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.29")

//
//    // Firebase Authentication (optional, if you need auth functionality)
//    implementation("com.google.firebase:firebase-auth")
//
//    // Firebase Analytics (optional, for analytics)
//    implementation("com.google.firebase:firebase-analytics")

//    // circle image view
//    implementation ("de.rhododendron:circumnavigate:3.1.0")
//
//    // picasso
//    implementation ("com.squareup.picasso:picasso:2.71828")
}