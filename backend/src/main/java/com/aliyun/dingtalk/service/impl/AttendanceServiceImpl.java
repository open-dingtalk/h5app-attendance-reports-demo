package com.aliyun.dingtalk.service.impl;

import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.service.AttendanceService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceGetupdatedataRequest;
import com.dingtalk.api.response.OapiAttendanceGetupdatedataResponse;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AppConfig appConfig;

    @Override
    public OapiAttendanceGetupdatedataResponse.AtCheckInfoForOpenVo getAttendanceReportsByUserId(String userId) {

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ATTENDANCE_DATA_URL);
        OapiAttendanceGetupdatedataRequest req = new OapiAttendanceGetupdatedataRequest();
        req.setUserid(userId);
        req.setWorkDate(StringUtils.parseDateTime("2021-06-17 09:00:00"));
        try {
            OapiAttendanceGetupdatedataResponse response = client.execute(req, AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret()));
            if (!Objects.isNull(response)) {
                if (response.isSuccess()) {
                    OapiAttendanceGetupdatedataResponse.AtCheckInfoForOpenVo result = response.getResult();
                    return result;
                } else {
                    log.error("getAttendanceReportsByUserId fail, errCode: {}, errMsg: {}", response.getErrcode(), response.getErrmsg());
                }
            } else {
                log.error("getAttendanceReportsByUserId error!");
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
