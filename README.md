# DLDateUtil 📅

[![最新版本](https://img.shields.io/badge/版本-2.1.1-blue.svg)](https://github.com/D10NGYANG/DLDateUtil/releases)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.10-purple.svg)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](License)

## 📱 平台支持

[![Android](https://img.shields.io/badge/平台-Android-6EDB8D.svg?style=flat&logo=android&logoColor=white)](https://developer.android.com)
[![iOS](https://img.shields.io/badge/平台-iOS-CDCDCD.svg?style=flat&logo=apple&logoColor=white)](https://developer.apple.com)
[![JVM](https://img.shields.io/badge/平台-JVM-DB413D.svg?style=flat&logo=java&logoColor=white)](https://www.java.com)
[![JS](https://img.shields.io/badge/平台-JavaScript-F8DB5D.svg?style=flat&logo=javascript&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
[![Native](https://img.shields.io/badge/平台-Native-7F52FF.svg?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/native-overview.html)

> 📢 版本1.2开始，本库已经改造成kotlin跨平台项目，目前支持Android、JVM、JS、iOS和Native平台。
>
> 📢 版本1.7.0开始取消 jitpack 仓库的支持

## 📖 项目简介

DLDateUtil 是一个基于 kotlinx-datetime 库的日期时间工具扩展库，旨在提供更丰富、更便捷的日期时间处理功能。本项目通过扩展 kotlinx-datetime 的核心类型，为开发者提供了一系列实用的日期时间操作方法，使日期时间的处理变得更加简单高效。

### ✨ 主要特点

- 🌐 **跨平台支持**：适用于 Android、iOS、JVM、JS 和 Native 平台
- 🔄 **丰富的日期时间转换功能**：各种时间类型之间的无缝转换
- ⏱️ **简化的时间戳处理**：轻松处理毫秒和秒级时间戳
- 📊 **日期计算与比较工具**：日期加减、间隔计算等
- 🔠 **格式化与解析功能**：灵活的日期时间格式化选项
- 🌙 **农历日期支持**：提供农历日期转换和计算

## 🧩 kotlinx-datetime 介绍

kotlinx-datetime 是 Kotlin 官方提供的跨平台日期时间处理库，它提供了一套简洁、一致且类型安全的 API 来处理日期和时间。以下是其核心抽象类型的介绍：

### ⚡ Instant

`Instant` 表示时间线上的一个瞬时点，是与时区无关的绝对时间点。它以 Unix 纪元（1970-01-01T00:00:00Z）为基准，记录了从该时间点经过的秒数和纳秒数。

**主要特点**：
- 🌍 表示绝对时间点，与时区无关
- 🔍 可用于精确的时间戳记录和时间间隔计算
- 🌐 适合在不同时区间进行时间转换和比较

### 📆 LocalDateTime

`LocalDateTime` 表示日期和时间，但不包含时区信息。它由 `LocalDate` 和 `LocalTime` 组合而成，表示"人类可读"的日期时间，如"2023-05-15 14:30:00"。

**主要特点**：
- 📝 表示特定日期和特定时间，但不绑定到特定时区
- 🎂 适合表示如生日、假日、会议时间等与时区无关的日期时间
- 🔄 可以通过指定时区转换为 `Instant`

### 📅 LocalDate

`LocalDate` 表示不带时间部分的日期，如"2023-05-15"。

**主要特点**：
- 📋 只包含年、月、日信息
- 🏆 适合表示生日、节假日、纪念日等纯日期信息
- 🧮 支持日期计算、比较和格式化

### ⏰ LocalTime

`LocalTime` 表示不带日期部分的时间，如"14:30:00"。

**主要特点**：
- 🕒 只包含时、分、秒、纳秒信息
- 📋 适合表示每日重复的时间点，如上下班时间、课程时间等
- ⚙️ 支持时间计算、比较和格式化

## 📚 使用说明

> 具体安装和使用请查看 [Wiki文档](https://github.com/D10NGYANG/DLDateUtil/wiki)

## 🔧 安装

```kotlin
// build.gradle.kts
dependencies {
    implementation("com.github.D10NGYANG:DLDateUtil:2.1.1")
}
```

## 📝 示例

```kotlin
// 获取当前时间
val now = now()
val timestamp = nowTimestamp()
val localDate = nowLocalDate()

// 日期时间转换
val instant = localDate.toSystemInstant()
val dateTime = instant.toSystemLocalDateTime()

// 格式化日期
val dateStr = localDate.format("yyyy-MM-dd")
```