# Gradle工程安装说明

## 1. 配置maven镜像地址
打开工程根目录的`build.gradle.kts`文件，修改`repositories`信息
```kotlin
repositories {
    mavenCentral()
    // multiplatform
    maven("https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository")
}
```

## 2. 添加依赖
打开工程根目录的`build.gradle.kts`文件，修改`dependencies`信息
```kotlin
dependencies {
    // 时间日期处理工具，可以使用最新版本号替换动态版本号+
    implementation("com.github.D10NGYANG:DLDateUtil:+")
}
```