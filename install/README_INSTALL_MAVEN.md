# Maven工程安装说明

## 1. 配置maven镜像地址
打开工程根目录的`pom.xml`文件，修改`repositories`信息
```xml
<repositories>
    <repository>
        <id>maven-repo</id>
        <url>https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository</url>
    </repository>
</repositories>
```

## 2. 添加依赖
打开工程根目录的`pom.xml`文件，修改`dependencies`信息
```xml
<dependencies>
    <!-- 时间日期处理工具，使用最新版本号替换version -->
    <dependency>
        <groupId>com.github.D10NGYANG</groupId>
        <artifactId>DLDateUtil</artifactId>
        <version>version</version>
    </dependency>
</dependencies>
```