plugins {
    kotlin("multiplatform") version "1.7.0"
    id("maven-publish")
    id("dev.petuska.npm.publish") version "3.0.3"
}

group = "com.github.D10NGYANG"
version = "1.7.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        moduleName = "dl-date-util"
        browser()
        binaries.library()
        binaries.executable()
    }
    ios {
        binaries {
            framework {}
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        val commonMain by getting {
            dependencies {
                // 时间工具
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("/Users/d10ng/project/kotlin/maven-repo/repository")
        }
    }
}

npmPublish {
    registries {
        register("npm-hosted") {
            uri.set("https://nexus.bds100.com/repository/npm-hosted")
        }
        register("npm-repo") {
            uri.set("/Users/d10ng/project/kotlin/maven-repo/repository")
            uri.au
        }
    }
}