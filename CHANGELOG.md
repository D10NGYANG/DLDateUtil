# 更新记录

## 3.0.0 — 2026-11-03

### 兼容性变化

- npm 导出的 Kotlin `Long` 参数和返回值使用 JavaScript `bigint`；运行环境必须支持 BigInt。旧 Kotlin Long 对象不可与新包混用。
- `toDateLong` 拒绝非法数字、日期、分隔符及冲突的重复字段，不再用当前时间静默替换坏字段。
- `Int` 秒时间戳转换向下取整；超出 `Int` 范围抛出 `IllegalArgumentException`，不再回绕。
- 毫秒构造参数必须为 `0..999`；旧 `setDateMillisecond` 仍按约定限制到有效范围。
- 范围外的 `lunarCalendar()` 抛出带说明的 `IllegalArgumentException`；可空农历转换和旧 `getDateDayLunar()` 返回 `null`。

### 修复

- 修正 ISO 跨年周数和月周数的周日边界，并纠正原测试的错误预期。
- 农历转换改为纯日期运算，修复正反转换一天偏移、夏令时/历史时区影响、闰月和日期范围校验。
- 修正节气解码、按立春切换的干支年和干支日基准。
- 修复 12 小时制解析崩溃、正午/午夜 AM/PM、重复 token 替换及单字符格式截断问题。
- 日期校验复用严格解析并正确处理字面分隔符；保留旧 API 忽略空白的验证行为。
- 日期复制保留合法历史年份，支持最大年份十二月而不跨年求月天数。
- 普通构建不再要求提供发布凭据。
- 修复 npm 发布插件临时目录残留导致新版本打包误用旧 tarball 的问题，并随 npm 包附带升级文档。

### 依赖与验证

- Gradle 8.14.6（更新 Wrapper 与官方 SHA-256）、Kotlin 2.2.21、AGP 8.10.1、kotlinx-datetime 0.8.0。
- Sonar Gradle 7.5.0.8588、Versions 0.67.0，后者迁移至 `io.github.ben-manes.versions`。
- 保留 npm 发布插件 3.5.3；Java 8 目标、Android minSdk 24 和 compileSdk 34 不变。
- JVM 40 项，JS、Android 单元测试、macOS Arm64 各 37 项通过；包含 73,384 天农历往返、40 年周数参考比对和多时区测试。
- 增加纯 JS 导出检查，验证独立 Kotlin、npm 和 TypeScript 消费者。

升级步骤见 [2.3.1 → 3.0.0](https://github.com/D10NGYANG/DLDateUtil/blob/3.0.0/docs/migration-3.0.0.md)。

## 2.3.1

历史发布说明见 [GitHub Release 2.3.1](https://github.com/D10NGYANG/DLDateUtil/releases/tag/2.3.1)。
