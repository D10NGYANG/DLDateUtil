val bds100MavenUsername: String by project
val bds100MavenPassword: String by project
val bds100NpmToken: String by project

plugins {
    kotlin("multiplatform") version "1.8.21"
    id("maven-publish")
    id("dev.petuska.npm.publish") version "3.3.1"
    id("org.sonarqube") version "4.2.0.3129"
}

group = "com.github.D10NGYANG"
version = "1.8.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
    }
    js(IR) {
        binaries.library()
        nodejs()
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
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
        }
        register("npm-releases") {
            uri.set("https://nexus.bds100.com/repository/npm-releases/")
            authToken.set(bds100NpmToken)
        }
    }
    packages {
        named("js") {
            packageName.set("dl-date-util")
        }
    }
}