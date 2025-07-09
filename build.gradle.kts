val bds100MavenUsername: String by project
val bds100MavenPassword: String by project
val npmJsToken: String by project

plugins {
    id("dev.petuska.npm.publish") version "3.5.3"
    kotlin("multiplatform") version "2.2.0"
    id("com.android.library")
    id("maven-publish")
    id("org.sonarqube") version "6.2.0.5505"
    id("com.github.ben-manes.versions") version "0.52.0"
}

group = "com.github.D10NGYANG"
version = "2.1.3"

repositories {
    google {
        mavenContent {
            includeGroupAndSubgroups("androidx")
            includeGroupAndSubgroups("com.android")
            includeGroupAndSubgroups("com.google")
        }
    }
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
    androidTarget {
        publishLibraryVariants("release")
    }
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) {
        outputModuleName = "dl-date-util"
        binaries.library()
        binaries.executable()
        nodejs()
        generateTypeScriptDefinitions()
    }
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    macosArm64()
    macosX64()
    linuxArm64()
    linuxX64()

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        commonMain {
            dependencies {
                // 时间工具
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    compileSdk = 34
    namespace = "$group.${rootProject.name}"

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications.withType(MavenPublication::class) {
        artifact(tasks["javadocJar"])
    }
    repositories {
        maven {
            url = uri("/Users/d10ng/project/kotlin/maven-repo/repository")
        }
        maven {
            credentials {
                username = bds100MavenUsername
                password = bds100MavenPassword
            }
            setUrl("https://nexus.bds100.com/repository/maven-releases/")
        }
    }
}

npmPublish {
    registries {
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
            authToken.set(npmJsToken)
        }
    }
    packages {
        named("js") {
            packageName.set("dl-date-util")
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

tasks.named<Copy>("jsNodeProductionLibraryDistribution") {
    // 显式声明依赖，确保先执行 jsProductionExecutableCompileSync 任务
    dependsOn("jsProductionExecutableCompileSync")
}