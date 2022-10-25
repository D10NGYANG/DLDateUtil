# Npm工程安装说明

## 1. 配置公司npm镜像
打开终端，输入下面命令：
```shell
npm config set @hailiao:registry=https://nexus.bds100.com/repository/npm-hosted/
```
> 表示@hailiao开头的库从这个镜像源获取

```shell
npm adduser --registry=https://nexus.bds100.com/repository/npm-hosted/ --scope=@hailiao
```
> 给公司镜像地址添加登录账户
> - 账户：onlyload
> - 密码：onlyload@
> - 邮箱：onlyload@hailiao.cn


## 2. 添加依赖
在项目根目录打开终端，输入下面命令：
```shell
npm install @hailiao/dl-date-util
```