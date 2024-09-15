
// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildScript {
//    apply(from = "variables.gradle.kts")


//    extra["kotlin_version"] = "1.9.10"
//    extra["compose_ui_version"] = "1.3.3"
//
//}

buildscript {
    apply(from = "functions.gradle.kts")
}

plugins {
    id("com.android.application") version "8.5.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.5.1" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
