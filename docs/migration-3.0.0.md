# 从 2.3.1 升级到 3.0.0

3.0.0 保留 Kotlin/JVM/Android 的函数签名，但修正了日期边界、非法输入和溢出的处理；npm 的 `Long` 表示存在破坏性变化。

## 更新依赖

```kotlin
implementation("com.github.D10NGYANG:DLDateUtil:3.0.0")
```

Maven 仓库为 `https://nexus.bds100.com/repository/maven-releases/`，访问方式见项目 README。库使用 Kotlin 2.2.21 编译，并通过 `api` 暴露 kotlinx-datetime 0.8.0；请检查项目中的强制版本约束是否仍锁在 0.7.1 或更早版本。

```sh
npm install dl-date-util@3.0.0
```

## JavaScript / TypeScript

所有导出的 Kotlin `Long` 参数和返回值现在是 `bigint`，`Int` 仍是 `number`。运行环境须支持 BigInt，TypeScript 项目可使用 `target: "ES2020"`。不要把旧版本生成的 Kotlin Long 对象传入新版本。

```javascript
const { com } = require("dl-date-util");
const dates = com.d10ng.datelib;

const milliseconds = dates.timestampUTCByYMD(2024, 2, 10); // 1707523200000n
const seconds = dates.timestampToTimeSeconds(milliseconds); // 1707523200
const current = BigInt(Date.now());
const text = dates.toDateStr(current, "yyyy-MM-dd HH:mm:ss");
```

- 从 `Date.now()` 或已验证的安全整数转入毫秒 API 时使用 `BigInt(value)`。
- JSON 不支持直接序列化 bigint：保存 `value.toString()`，读取后用 `BigInt(text)` 恢复。
- 只有确认值处于 `Number.MIN_SAFE_INTEGER..Number.MAX_SAFE_INTEGER` 后才用 `Number(value)`；大整数若先转为 number 再转回 bigint，精度无法恢复。
- `asLong(number)` 返回值也变为 bigint；传入 number 前仍须自行保证其精度。

## 日期解析与格式化

`toDateLong` 现在对非法字段、错误分隔符及冲突字段抛出 `IllegalArgumentException`。例如 `abcd-01-01 00:00:00` 不再变成当前年份的一月一日，`2023-02-31` 也不再通过日期校验。

需要用户输入校验时使用 `isDatetimeString(pattern)`，或在解析处处理 `IllegalArgumentException`。该校验函数保留忽略空白的旧行为，而 `toDateLong` 要求输入匹配模式。

保留的旧模式语义：

- `Y` 等同公历年份 `y`，不是 ISO 周年；`w/W/D/E` 在解析时仅匹配，不参与构造日期。
- 缺省的年月日取当前日期；缺省的时分秒取零。
- `yy` 格式化取两位年份，解析仍按字面年份处理，不自动推断世纪。
- `S/SS/SSS` 表示毫秒精度，其他宽度报错。
- 单字符 `M/d/H/m/s` 不再截断高位；正午、午夜和 `hh:mm a` 按正确的 12 小时制处理。

不要把旧模式直接替换成 Unicode pattern；两套格式的部分字母语义不同。

## 秒数与毫秒范围

`timestampToTimeSeconds`、`Instant.toTimeSeconds` 及相关入口使用向下取整：`-1` 毫秒得到 `-1` 秒。秒数超出 `Int` 范围时抛错，例如 2038-01-19 03:14:08 UTC。

需要更大范围的秒数时使用：

```kotlin
@OptIn(kotlin.time.ExperimentalTime::class)
fun epochSeconds(milliseconds: Long): Long =
    milliseconds.timestampToInstant().epochSeconds
```

时间戳构造方法会先检查毫秒在 `0..999` 范围内，避免乘法溢出绕过检查；旧 `setDateMillisecond` 仍将输入限制到此范围。

## 农历、周数与日期复制

- 农历支持公历 `1900-01-31..2100-12-31`；`solar2lunar`、`lunar2solar` 对无效输入或范围外结果返回 null。`lunarCalendar()` 对范围外日期抛出明确异常，旧可空 `getDateDayLunar()` 保留 null。
- 农历、节气和干支只由公历日期决定，系统时区不再改变结果；`isToday` 仍遵循系统日期。
- 干支年以立春换年，生肖以农历年换年。
- 2024 年正月初一正确对应 2024-02-10；2018-12-31 的 ISO 周数为 1；2023-01-15 的 `weekOfMonth(false)` 为 3。
- `copy()` 不再将 1970 年前的合法年份改成 1970；月末回退仍保留。

如果业务曾保存受旧算法影响的派生农历/周数值，应从原始日期重新计算，并在更新历史数据前核对业务影响。

## 构建与验收

已验证的组合为 Gradle 8.14.6、Kotlin 2.2.21、AGP 8.10.1；没有迁移到 AGP 9。Gradle 运行 JDK 与库的 Java 8 编译目标应分开配置。

```sh
./gradlew jvmTest jsNodeTest testDebugUnitTest macosArm64Test packJsPackage
node src/jsTest/js/exports.cjs build/js/packages/dl-date-util
```

`macosArm64Test` 需 Apple Silicon macOS。发布前还需验证目标平台的产物和下游调用方；本次 iOS 目标完成编译，未声称进行真机测试。
