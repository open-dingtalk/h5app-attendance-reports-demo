package com.aliyun.dingtalk.service;

import com.aliyun.dingtalk.model.AttendanceStatistics;
import com.dingtalk.api.response.OapiAttendanceGetupdatedataResponse;

import java.util.List;

public interface AttendanceService {
    OapiAttendanceGetupdatedataResponse.AtCheckInfoForOpenVo getAttendanceReportsByUserId(String userId, String workDate);

    List<AttendanceStatistics> getAttendanceReports(String userId, String fromDate, String toDate);
}
