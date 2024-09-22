plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id(libs.plugins.ksp.get().toString())
    id(libs.plugins.daggerHilt.get().toString())

}

val configProperties = BuildConfig.projectConfigurations(project)


android {
    namespace = libs.plugins.networkNamespace.get().toString()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        configProperties.forEach { key, value ->
            buildConfigField("String", key.toString(), "\"${value}\"")
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
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":local"))

    //Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.core)

    //Moshi
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    //Retrofit
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.retrofit.gson.converter)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}