# DLDateUtil 📅

[![最新版本](https://img.shields.io/badge/版本-3.0.0-blue.svg)](https://github.com/D10NGYANG/DLDateUtil/releases)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-purple.svg)](https://kotlinlang.org)
[![Kotlinx-datetime](https://img.shields.io/badge/Kotlinx--datetime-0.8.0-green.svg)](https://github.com/Kotlin/kotlinx-datetime)
[![License](https://img.shields.io/badge/License-MIT-lightgray.svg)](License)

## 📱 平台支持

[![Android](https://img.shields.io/badge/平台-Android-6EDB8D.svg?style=flat&logo=android&logoColor=white)](https://developer.android.com)
[![iOS](https://img.shields.io/badge/平台-iOS-CDCDCD.svg?style=flat&logo=apple&logoColor=white)](https://developer.apple.com)
[![JVM](https://img.shields.io/badge/平台-JVM-DB413D.svg?style=flat&logo=java&logoColor=white)](https://www.java.com)
[![JS](https://img.shields.io/badge/平台-JavaScript-F8DB5D.svg?style=flat&logo=javascript&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
[![Native](https://img.shields.io/badge/平台-Native-7F52FF.svg?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/native-overview.html)

> 📢 版本1.2开始，本库已经改造成kotlin跨平台项目，目前支持Android、JVM、JS、iOS和Native平台。
>
> 📢 版本1.7.0开始取消 jitpack 仓库的支持
> 
> 📢 版本2.3.0开始使用kotlin:2.2.0、kotlinx-datetime:0.7.1环境，其中部分官方API弃用，从kotlinx.datetime移到了kotlin.time中，需要参考[Deprecation of Instant](https://github.com/Kotlin/kotlinx-datetime)进行迁移；

## 3.0.0 升级提示

3.0.0 包含破坏性变更：npm 的毫秒时间戳改为 `bigint`，非法日期输入和越界秒数会明确报错。升级前请阅读 [2.3.1 → 3.0.0 升级指南](https://github.com/D10NGYANG/DLDateUtil/blob/3.0.0/docs/migration-3.0.0.md)；完整变化见 [CHANGELOG](https://github.com/D10NGYANG/DLDateUtil/blob/3.0.0/CHANGELOG.md)。

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
repositories {
    maven("https://nexus.bds100.com/repository/maven-releases/") {
        credentials {
            username = providers.gradleProperty("bds100MavenUsername").orNull
            password = providers.gradleProperty("bds100MavenPassword").orNull
        }
    }
    mavenCentral()
}
dependencies {
    implementation("com.github.D10NGYANG:DLDateUtil:3.0.0")
}
```

npm：

```sh
npm install dl-date-util@3.0.0
```

Maven 仓库需要访问凭据，请放在本机 Gradle 属性或 CI 密钥中；npm 包为公开包。

## 📝 示例

```kotlin
import com.d10ng.datelib.*
import kotlinx.datetime.LocalDate

val timestamp = timestampUTC(2024, 2, 10)
val lunar = LocalDate(2024, 2, 10).lunarCalendar()
check(lunar.lMonth == 1 && lunar.lDay == 1)
check(LocalDate(2018, 12, 31).isoWeekOfYear() == 1)
```

## 日期边界与兼容性

- 农历转换支持公历 `1900-01-31` 至 `2100-12-31`；`solar2lunar`、`lunar2solar` 对无效日期或超出范围返回 `null`。`lunarCalendar()` 对范围外日期抛出 `IllegalArgumentException`，旧的可空接口 `getDateDayLunar()` 仍返回 `null`。
- 农历、节气及干支日期不受系统时区影响；干支年按立春换年，生肖按农历年计算。
- `isoWeekOfYear()` 按 ISO 周规则计算；`weekOfMonth(false)` 将月初所在周计为第一周，每逢周一递增。
- 日期 `copy()` 保留合法历史年份及底层类型支持的年份，月末回退规则不变。
- 秒级 `Int` API 向下取整，超出 `Int` 范围会抛出 `IllegalArgumentException`，不再回绕。需要完整范围的秒数时使用 `timestampToInstant().epochSeconds`（`Long`）。毫秒构造参数必须在 `0..999`；旧 `setDateMillisecond` 按其既有约定将输入限制在此范围。
- 旧 `toDateStr` / `toDateLong` 保留自定义模式语义：`Y` 等同于公历年份 `y`，`w/W/D/E` 在解析时不参与日期构造。数值宽度表示最少补零位数，`yy` 格式化取年份末两位；解析两位年份仍按字面年份处理，不推断世纪。`S/SS/SSS` 为毫秒精度，其他宽度会报错。
- `toDateLong` 拒绝非法数字、日期、分隔符及冲突的重复字段；未指定的年月日取当前日期，时分秒默认零。`isDatetimeString` 使用相同日期检查并保持忽略空白的行为。
- 普通构建、测试无需发布凭据；发布到 Maven / npm 时才需要相应的 `bds100MavenUsername`、`bds100MavenPassword` / `npmJsToken`。

回归测试：`./gradlew jvmTest jsNodeTest testDebugUnitTest macosArm64Test`（macOS Native 任务需在 Apple Silicon macOS 上运行）。


## JavaScript 时间戳接口迁移

Kotlin 2.2.21 的 JS 构建启用了 `-Xes-long-as-bigint`。npm 导出的 Kotlin `Long` 参数和返回值现在使用 JavaScript `bigint`，运行环境必须支持 BigInt；这是一项 JS 接口兼容性变化，旧 Kotlin Long 对象不能与新产物混用。Kotlin、JVM 和 Android 的函数签名不变。

```javascript
const { com } = require("dl-date-util");
const dates = com.d10ng.datelib;
const timestamp = dates.timestampUTCByYMD(2024, 2, 10); // bigint
const seconds = dates.timestampToTimeSeconds(timestamp); // number，Int 范围
const fromJsDate = BigInt(Date.now());
```

对接 JSON 时使用十进制字符串保存 bigint；仅在确认处于 JavaScript 安全整数范围后转为 number。本次以 3.0.0 主版本发布这一兼容性变化；调用方应同步迁移。


本地验证 npm 导出（先执行 `./gradlew packJsPackage`）：

```sh
node src/jsTest/js/exports.cjs build/js/packages/dl-date-util
```
