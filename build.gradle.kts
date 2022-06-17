plugins {
    kotlin("multiplatform") version "1.6.10"
    id("maven-publish")
}

group = "com.github.D10NGYANG"
version = "1.6"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js {
        browser {}
        binaries.executable()
    }
    macosX64()
    ios()
    mingwX64()
    linuxX64()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // 时间工具
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.3.3")
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