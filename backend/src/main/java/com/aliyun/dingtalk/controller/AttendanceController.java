package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.model.RpcServiceResult;
import com.aliyun.dingtalk.service.AttendanceService;
import com.aliyun.dingtalk.service.DingTalkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


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
    public RpcServiceResult getAttendanceReportsByUserId(@RequestParam String userId, @RequestParam String workDate) {

        return RpcServiceResult.getSuccessResult(attendanceService.getAttendanceReportsByUserId(userId, workDate));

    }

    /**
     * 根据日期范围统计用户在智能考勤上面配置的的考勤信息，最多统计31天的数据
     *
     * @param
     * @return
     */
    @GetMapping("/attendance/intelligence")
    public RpcServiceResult getAttendanceReports(@RequestParam String userId, @RequestParam String fromDate, @RequestParam String toDate) {

        return RpcServiceResult.getSuccessResult(attendanceService.getAttendanceReports(userId, fromDate, toDate));

    }
}
