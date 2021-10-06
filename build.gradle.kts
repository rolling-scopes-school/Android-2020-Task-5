// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.1")
}

detekt {
    toolVersion = "1.10.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true

    reports {
        html {
            enabled = true
            destination = file("app/build/detekt/detekt.html")
        }
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
