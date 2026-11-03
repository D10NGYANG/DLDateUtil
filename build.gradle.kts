plugins {
    id("dev.petuska.npm.publish") version "3.5.3"
    kotlin("multiplatform") version "2.2.21"
    id("com.android.library")
    id("maven-publish")
    id("org.sonarqube") version "7.5.0.8588"
    id("io.github.ben-manes.versions") version "0.67.0"
}

group = "com.github.D10NGYANG"
version = "3.0.0"

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
        compilerOptions {
            freeCompilerArgs.add("-Xes-long-as-bigint")
        }
        outputModuleName = "dl-date-util"
        binaries.library()
        //binaries.executable()
        nodejs {
            testTask {
                useMocha {
                    timeout = "60s" // 农历支持区间逐日往返测试覆盖约 7.3 万个日期。
                }
            }
        }
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
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0")
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
                username = providers.gradleProperty("bds100MavenUsername").orNull
                password = providers.gradleProperty("bds100MavenPassword").orNull
            }
            setUrl("https://nexus.bds100.com/repository/maven-releases/")
        }
    }
}

npmPublish {
    registries {
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
            authToken.set(providers.gradleProperty("npmJsToken"))
        }
    }
    packages {
        named("js") {
            packageName.set("dl-date-util")
            readme.set(layout.projectDirectory.file("README.md"))
            files.from("License", "CHANGELOG.md", "docs")
            packageJson {
                description.set("Kotlin Multiplatform date, time and lunar calendar utilities")
                license.set("MIT")
                homepage.set("https://github.com/D10NGYANG/DLDateUtil")
            }
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


tasks.withType<dev.petuska.npm.publish.task.NpmPackTask>().configureEach {
    // npm-publish 3.5.3 取临时目录第一个文件，避免把旧 tarball 当作新版本。
    doFirst { delete(fileTree(temporaryDir) { include("*.tgz") }) }
}
