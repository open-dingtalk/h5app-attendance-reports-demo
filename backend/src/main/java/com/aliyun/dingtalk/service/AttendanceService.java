package com.aliyun.dingtalk.service;

import com.dingtalk.api.response.OapiAttendanceGetupdatedataResponse;

public interface AttendanceService {
    OapiAttendanceGetupdatedataResponse.AtCheckInfoForOpenVo getAttendanceReportsByUserId(String userId);
}
