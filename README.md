# h5app-attendance-reports-demo

> 钉钉智能考勤统计，接入智能考勤登记需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中添加“查询企业考勤数据权限“；需要[开启考勤智能统计](https://developers.dingtalk.com/document/app/enable-intelligent-statistics)。

## 配置应用首页地址

![image-20210628163247545](https://img.alicdn.com/imgextra/i3/O1CN01YlfHQA22ZscKkxu0g_!!6000000007135-2-tps-2774-1176.png)

## 配置权限

### 添加成员信息读取权限

![image-20210628161245415](https://img.alicdn.com/imgextra/i4/O1CN01fvqz0z1J8iQ1XSiRi_!!6000000000984-2-tps-2828-1200.png)



### 添加查询企业考勤数据权限

![image-20210628145528700](/Users/wan/Library/Application Support/typora-user-images/image-20210628145528700.png)

## Getting Started

### 克隆代码仓库到本地

git clone

```
https://github.com/open-dingtalk/h5app-attendance-reports-demo.git
```

### 使用命令行安装依赖&打包

```txt
cd fronted/
```

![image-20210628145702026](/Users/wan/Library/Application Support/typora-user-images/image-20210628145702026.png)

```txt
npm install
```

![image-20210628150426230](/Users/wan/Library/Application Support/typora-user-images/image-20210628150426230.png)

```txt
npm run build
```

![image-20210628150544789](/Users/wan/Library/Application Support/typora-user-images/image-20210628150544789.png)

### 将打包好的静态资源文件放入后端服务

![image-20210628150638110](/Users/wan/Library/Application Support/typora-user-images/image-20210628150638110.png)

### 替换后端应用配置

![image-20210628150707755](/Users/wan/Library/Application Support/typora-user-images/image-20210628150707755.png)



### 参考文档

1. 考勤统计权限申请，文档链接：https://developers.dingtalk.com/document/app/apply-for-permissions
2. 启用智能统计，文档链接：https://developers.dingtalk.com/document/app/enable-intelligent-statistics
3. 获取报表列定义，文档链接：https://developers.dingtalk.com/document/app/queries-the-enterprise-attendance-report-column
4. 获取报表列值，文档链接：https://developers.dingtalk.com/document/app/obtains-the-column-values-of-the-smart-attendance-report
5. 获取用户考勤数据，文档链接：https://developers.dingtalk.com/document/app/obtain-the-attendance-update-data
