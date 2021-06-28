## h5app-attendance-reports-demo

> 钉钉智能考勤统计，接入智能考勤登记需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中添加“查询企业考勤数据权限“；需要[开启考勤智能统计](https://developers.dingtalk.com/document/app/enable-intelligent-statistics)。

##### 查询企业考勤数据权限

![image-20210628114733992](/Users/wan/Library/Application Support/typora-user-images/image-20210628114733992.png)

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

![image-20210617180814765](/Users/wan/Library/Application Support/typora-user-images/image-20210617180814765.png)

```txt
npm install
```

![image-20210617180910689](/Users/wan/Library/Application Support/typora-user-images/image-20210617180910689.png)

```txt
npm run build
```

![image-20210617181053688](/Users/wan/Library/Application Support/typora-user-images/image-20210617181053688.png)

### 将打包好的静态资源文件放入后端服务

![img](https://alidocs.oss-cn-zhangjiakou.aliyuncs.com/a/RNErEm4k2S2KW0jV/714875c9b4294970b07004542047fc672460.png)

### 替换后端应用配置

![image-20210617181426867](/Users/wan/Library/Application Support/typora-user-images/image-20210617181426867.png)

### 启动项目使用钉钉访问服务



### 参考文档

1. 智能工作流权限申请，文档链接：https://developers.dingtalk.com/document/app/logon-free-process?spm=ding_open_doc.document.0.0.6dbdad29INJCRg#topic-2025319
2. 工作流审批表单，文档链接：https://developers.dingtalk.com/document/app/workflow-overview
3. 发起审批实例，文档链接：https://developers.dingtalk.com/document/app/initiate-approval
4. 获取实例详情，文档链接：https://developers.dingtalk.com/document/app/obtains-the-details-of-a-single-approval-instance
5. 事件回调，文档链接：https://developers.dingtalk.com/document/app/callback-overview
