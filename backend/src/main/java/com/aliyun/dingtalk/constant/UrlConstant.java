package com.aliyun.dingtalk.constant;

/**
 * 钉钉开放接口网关常量
 */
public class UrlConstant {

    /**
     * 获取access_token url
     */
    public static final String GET_ACCESS_TOKEN_URL = "https://oapi.dingtalk.com/gettoken";
    /**
     * 通过免登授权码获取用户信息 url
     */
    public static final String GET_USER_INFO_URL = "https://oapi.dingtalk.com/topapi/v2/user/getuserinfo";
    /**
     * 根据用户id获取用户详情 url
     */
    public static final String USER_GET_URL = "https://oapi.dingtalk.com/topapi/v2/user/get";

    /**
     * 根据userId和查询日期获取用户考勤数据
     */
    public static final String GET_ATTENDANCE_DATA_URL = "https://oapi.dingtalk.com/topapi/attendance/getupdatedata";

    /**
     * 获取报表列定义url
     */
    public static final String GET_ATTENDANCE_COLUMNS_URL = "https://oapi.dingtalk.com/topapi/attendance/getattcolumns";

    /**
     * 获取报表列定义值url
     */
    public static final String GET_ATTENDANCE_COLUMNS_VALUE_URL = "https://oapi.dingtalk.com/topapi/attendance/getcolumnval";


}
