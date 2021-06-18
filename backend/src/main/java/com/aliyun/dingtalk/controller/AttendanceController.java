package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.model.RpcServiceResult;
import com.aliyun.dingtalk.service.AttendanceService;
import com.aliyun.dingtalk.service.DingTalkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 钉钉h5企业内部应用DEMO, 实现了根据用户授权码获取用户信息功能
 */
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 根据userId获取用户考勤统计信息
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/attendance/{userId}")
    public RpcServiceResult getAttendanceReportsByUserId(@PathVariable String userId) {

        return RpcServiceResult.getSuccessResult(attendanceService.getAttendanceReportsByUserId(userId));

    }
}
