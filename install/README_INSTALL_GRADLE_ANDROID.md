# Android工程安装说明

## 1. 配置maven镜像地址
### Gradle版本小于7
打开工程根目录的`build.gradle.kts`文件，修改`repositories`信息
```kotlin
allprojects {
    repositories {
        mavenCentral()
        maven("https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository")
    }
}
```
### Gradle版本大于等于7
打开工程根目录的`settings.gradle.kts`文件，修改`repositories`信息
```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository")
    }
}
```

## 2. 添加依赖
打开工程目录的`build.gradle.kts`文件，修改`dependencies`信息
```kotlin
android {
    defaultConfig {
        // 将minSdkVersion设置为20或更低时需要
        multiDexEnabled = true
    }
    compileOptions {
        // 允许脱糖
        isCoreLibraryDesugaringEnabled = true
    }
}
dependencies {
    // 时间日期处理工具，可以使用最新版本号替换动态版本号+
    implementation("com.github.D10NGYANG:DLDateUtil:+")
    // Java 8 及更高版本 API 脱糖支持 https://developer.android.com/studio/write/java8-support#library-desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}
```

## 3. 混淆
```properties
-keep class com.d10ng.datelib.** {*;}
-dontwarn com.d10ng.datelib.**
```
