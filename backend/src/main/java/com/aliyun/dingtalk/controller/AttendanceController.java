package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 钉钉h5企业内部应用DEMO, 实现了获取用户考勤信息的功能
 */
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 根据userId和工作日期获取用户考勤统计信息
     *
     * @param userId 用户ID
     * @param workDate 工作日期
     * @return
     */
    @GetMapping("/attendance")
    public ServiceResult getAttendanceReportsByUserId(@RequestParam String userId, @RequestParam String workDate) {

        return ServiceResult.getSuccessResult(attendanceService.getAttendanceReportsByUserId(userId, workDate));

    }

    /**
     * 根据日期范围统计用户在智能考勤上面配置的的考勤信息，最多统计31天的数据
     *
     * @param
     * @return
     */
    @GetMapping("/attendance/intelligence")
    public ServiceResult getAttendanceReports(@RequestParam String userId, @RequestParam String fromDate, @RequestParam String toDate) {

        return ServiceResult.getSuccessResult(attendanceService.getAttendanceReports(userId, fromDate, toDate));

    }
}
