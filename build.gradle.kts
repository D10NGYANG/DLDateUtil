val bds100MavenUsername: String by project
val bds100MavenPassword: String by project

plugins {
    kotlin("multiplatform") version "1.8.10"
    id("maven-publish")
    id("dev.petuska.npm.publish") version "3.2.1"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "com.github.D10NGYANG"
version = "1.7.2"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
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
        register("npm-hosted") {
            uri.set("https://nexus.bds100.com/repository/npm-hosted")
        }
    }
    packages {
        named("js") {
            scope.set("hailiao")
            packageName.set("dl-date-util")
            dependencies {
                normal("@js-joda/core", "*")
            }
        }
    }
}