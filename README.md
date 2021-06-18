# DLDateUtil
日期时间工具[![](https://jitpack.io/v/D10NGYANG/DLDateUtil.svg)](https://jitpack.io/#D10NGYANG/DLDateUtil)

## 使用
1 Add it in your root build.gradle at the end of repositories:
```xml
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2 Add the dependency
```xml
dependencies {
        implementation 'com.github.D10NGYANG:DLDateUtil:1.0'
}
```
3 混淆
```xml
-keep class com.d10ng.datelib.** {*;}
-dontwarn com.d10ng.datelib.**
```
