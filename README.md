# h5app-attendance-reports-demo

> 钉钉智能考勤统计，接入智能考勤登记需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中添加“查询企业考勤数据权限“；需要[开启考勤智能统计](https://developers.dingtalk.com/document/app/enable-intelligent-statistics)。架构形态是一个Java单体应用，钉钉用户可以在页面上点击**获取用户考勤信息**根据日期和用户ID获取用户考勤信息，点击**获取用户智能考勤统计信息**根据日期范围统计用户考勤信息。包含功能：

- 获取用户考勤信息：调用本接口获取指定用户当天的考勤数据，包括打卡流水记录、打卡结果和审批列表等；
- 获取用户智能考勤统计信息：该接口用于获取钉钉智能考勤报表的列值数据，其中包含了一定时间段内报表某一列的所有数据，以及相关的列信息，不支持获取离职人员的考勤信息，离职人员的考勤数据可以在[OA管理后台](https://attend.dingtalk.com/portal/index.html)查询。

## 开发环境准备

#### 钉钉开放平台环境准备

1. 需要有一个钉钉注册企业，如果没有可以创建：https://oa.dingtalk.com/register_new.htm?source=1008_OA&lwfrom=2018122711522903000&succJump=oa#/

2. 成为钉钉开发者，参考文档：https://developers.dingtalk.com/document/app/become-a-dingtalk-developer

3. 登录钉钉开放平台后台创建一个H5应用： https://open-dev.dingtalk.com/#/index

4. 配置应用

   配置开发管理，参考文档：https://developers.dingtalk.com/document/app/configure-orgapp

   ![image-20210628163247545](https://img.alicdn.com/imgextra/i3/O1CN01YlfHQA22ZscKkxu0g_!!6000000007135-2-tps-2774-1176.png)

   配置免登相关权限：https://developers.dingtalk.com/document/app/address-book-permissions

   ![image-20210628161245415](https://img.alicdn.com/imgextra/i4/O1CN01fvqz0z1J8iQ1XSiRi_!!6000000000984-2-tps-2828-1200.png)

   添加查询企业考勤数据权限

   ![image-20210705102945255](https://img.alicdn.com/imgextra/i3/O1CN01SFhtSk1PMLuCMuMzL_!!6000000001826-2-tps-2872-1122.png)

#### 获取H5钉钉应用的参数

```properties
#钉钉组织ID
corpId=xxxxx
#H5应用Key
appKey=xxxx
#H5应用秘钥
appSecret=xxxxxx
```

#### 钉钉应用参数需要登陆开发者后台

1. 首页获取corpId https://open-dev.dingtalk.com/#/index
2. 进入应用-基础信息获取appKey、appSecret

## Getting Started

### 克隆代码仓库到本地

git clone

```
https://github.com/open-dingtalk/h5app-attendance-reports-demo.git
```

### 使用命令行安装依赖&打包

```txt
cd frontend/
```

![image-20210705105306834](https://img.alicdn.com/imgextra/i1/O1CN014KH6rx1lO6yOHJUkP_!!6000000004808-2-tps-2624-630.png)

```txt
npm install
```

![image-20210705110554943](https://img.alicdn.com/imgextra/i1/O1CN019Ru42o29GN3irhee4_!!6000000008040-2-tps-2384-966.png)

```txt
npm run build
```

![image-20210705110816362](https://img.alicdn.com/imgextra/i2/O1CN01KtYc6M28WZS7RlSOF_!!6000000007940-2-tps-2032-1256.png)

### 将打包好的静态资源文件放入后端服务

![image-20210705110939301](https://img.alicdn.com/imgextra/i3/O1CN01yNFkQb1Mx2n2qoLSC_!!6000000001500-2-tps-2334-1114.png)

### 替换后端应用配置

![image-20210705111407632](https://img.alicdn.com/imgextra/i4/O1CN0102QFwm1XOep6TxiIy_!!6000000002914-2-tps-2152-1054.png)



### 参考文档

1. 考勤统计权限申请，文档链接：https://developers.dingtalk.com/document/app/apply-for-permissions
2. 启用智能统计，文档链接：https://developers.dingtalk.com/document/app/enable-intelligent-statistics
3. 获取报表列定义，文档链接：https://developers.dingtalk.com/document/app/queries-the-enterprise-attendance-report-column
4. 获取报表列值，文档链接：https://developers.dingtalk.com/document/app/obtains-the-column-values-of-the-smart-attendance-report
5. 获取用户考勤数据，文档链接：https://developers.dingtalk.com/document/app/obtain-the-attendance-update-data
