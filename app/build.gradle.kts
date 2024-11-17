plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}


android {
    namespace = "com.app.movies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.movies"
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
    val flavorDimension = "default"

    flavorDimensions(flavorDimension)
    productFlavors {
        //TODO change endpoint
        create("staging") {
            dimension = flavorDimension
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://www.karanpratapsingh.com/courses/system-design/ip/\""
            )
            buildConfigField("boolean", "enableNetworkLogging", "true")
        }

        //TODO change endpoint
        create("production") {
            dimension = flavorDimension
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://www.karanpratapsingh.com/courses/system-design/ip/\""
            )
            buildConfigField("boolean", "enableNetworkLogging", "false")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.coil.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.googleGson)
    implementation(libs.hiltNavigationCompose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.bundles.retrofit)
    implementation(libs.chucker.library)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.kotlin.codegen)
    //implementation(libs.mo)
    // implementation(libs.chucker.library.noop)
    implementation(libs.okhttp3.logging.interceptor)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation(libs.accompanist.systemuicontroller)


}