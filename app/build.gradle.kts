plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.muneeb.socialscape"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.muneeb.socialscape"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Navigation
    //def navVersion = ("2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    //Splash screen
    implementation("androidx.core:core-splashscreen:1.0.0")

    //Sizing
//    implementation("com.intuit.sdp:sdp-android:1.1.0")


    implementation ("me.grantland:autofittextview:0.2.1")

    implementation ("com.github.luongvo:iOS-SwitchView:1.0.2")

    implementation ("com.github.iielse:switchbutton:1.0.4")

    implementation ("com.github.zcweng:switch-button:0.0.3@aar")

    implementation ("com.github.addisonelliott:SegmentedButton:3.1.9")

}