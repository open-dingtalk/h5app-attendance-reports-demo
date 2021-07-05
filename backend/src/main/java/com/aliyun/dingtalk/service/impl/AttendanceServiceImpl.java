package com.aliyun.dingtalk.service.impl;

import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.exception.InvokeDingTalkException;
import com.aliyun.dingtalk.model.AttendanceStatistics;
import com.aliyun.dingtalk.service.AttendanceService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceGetattcolumnsRequest;
import com.dingtalk.api.request.OapiAttendanceGetcolumnvalRequest;
import com.dingtalk.api.request.OapiAttendanceGetupdatedataRequest;
import com.dingtalk.api.response.OapiAttendanceGetattcolumnsResponse;
import com.dingtalk.api.response.OapiAttendanceGetcolumnvalResponse;
import com.dingtalk.api.response.OapiAttendanceGetupdatedataResponse;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AppConfig appConfig;

    @Override
    public OapiAttendanceGetupdatedataResponse.AtCheckInfoForOpenVo getAttendanceReportsByUserId(String userId, String workDate) {

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ATTENDANCE_DATA_URL);
        OapiAttendanceGetupdatedataRequest req = new OapiAttendanceGetupdatedataRequest();
        req.setUserid(userId);
        try {
            req.setWorkDate(DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse(workDate));
            OapiAttendanceGetupdatedataResponse response = client.execute(req, AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret()));
            if (response.isSuccess()) {
                OapiAttendanceGetupdatedataResponse.AtCheckInfoForOpenVo result = response.getResult();
                return result;
            } else {
                throw new InvokeDingTalkException(response.getErrorCode(), response.getErrmsg());
            }

        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("date parse exception, pattern is " + DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT);
        }
    }

    /**
     * 获取企业智能考勤报表数据
     *
     * @return
     */
    @Override
    public List<AttendanceStatistics> getAttendanceReports(String userId, String fromDate, String toDate) {

        // 获取企业accessToken
        String accessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());

        // 获取考勤智能统计定义的列
        List<OapiAttendanceGetattcolumnsResponse.ColumnForTopVo> attendanceColumns = getAttendanceColumns(accessToken);

        // 列ID和别名映射
        Map<String, String> columnIdAndAliasMap = new HashMap<>();

        // 列ID和名称映射
        Map<String, String> columnIdAndNameMap = new HashMap<>();

        attendanceColumns.stream().filter(columnForTopVo -> columnForTopVo.getId() != null).forEach(columnForTopVo -> {

                    columnIdAndAliasMap.put(String.valueOf(columnForTopVo.getId()), columnForTopVo.getAlias());
                    columnIdAndNameMap.put(String.valueOf(columnForTopVo.getId()), columnForTopVo.getName());
                }
        );

        // 根据列ID获取列值，此处注意列ID的数量不能超过20个，如果超过需要分批处理
        List<String> columnIds = columnIdAndAliasMap.keySet().stream().collect(Collectors.toList());
        List<List<String>> partitionColumnIds = Lists.partition(columnIds, 20);

        List<AttendanceStatistics> attendanceStatisticsList = new ArrayList<>();
        partitionColumnIds.forEach(columnIdList -> {

            // 获取列值
            List<OapiAttendanceGetcolumnvalResponse.ColumnValForTopVo> attendanceColumnsValues = getAttendanceColumnsValue(accessToken, userId, columnIdList, fromDate, toDate);

            attendanceStatisticsList.addAll(attendanceColumnsValues.stream().map(attendanceColumnsValue -> {
                AttendanceStatistics attendanceStatistics = new AttendanceStatistics();
                Long columnId = attendanceColumnsValue.getColumnVo().getId();
                attendanceStatistics.setId(columnId);
                attendanceStatistics.setAlias(columnIdAndAliasMap.get(String.valueOf(columnId)));
                attendanceStatistics.setName(columnIdAndNameMap.get(String.valueOf(columnId)));
                attendanceStatistics.setColumnVals(attendanceColumnsValue.getColumnVals());
                return attendanceStatistics;
            }).collect(Collectors.toList()));

        });


        return attendanceStatisticsList;
    }

    /**
     * 获取企业智能考勤报表中的列信息
     *
     * @param accessToken
     * @return
     */
    private List<OapiAttendanceGetattcolumnsResponse.ColumnForTopVo> getAttendanceColumns(String accessToken) {

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ATTENDANCE_COLUMNS_URL);
        OapiAttendanceGetattcolumnsRequest req = new OapiAttendanceGetattcolumnsRequest();
        try {
            OapiAttendanceGetattcolumnsResponse rsp = client.execute(req, accessToken);
            if (rsp.isSuccess()) {
                OapiAttendanceGetattcolumnsResponse.AttColumnsForTopVo result = rsp.getResult();
                List<OapiAttendanceGetattcolumnsResponse.ColumnForTopVo> columns = result.getColumns();
                return columns;
            } else {
                throw new InvokeDingTalkException(rsp.getErrorCode(), rsp.getErrmsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 获取企业智能考勤报表中的列信息值
     *
     * @param accessToken
     * @return
     */
    private List<OapiAttendanceGetcolumnvalResponse.ColumnValForTopVo> getAttendanceColumnsValue(String accessToken, String userId, List<String> columnIdList, String fromDate, String toDate) {

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ATTENDANCE_COLUMNS_VALUE_URL);
        OapiAttendanceGetcolumnvalRequest req = new OapiAttendanceGetcolumnvalRequest();
        req.setUserid(userId);
        req.setColumnIdList(columnIdList.stream().collect(Collectors.joining(",")));
        try {
            req.setFromDate(DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse(fromDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            req.setToDate(DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse(toDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            OapiAttendanceGetcolumnvalResponse rsp = client.execute(req, accessToken);
            if (rsp.isSuccess()) {
                OapiAttendanceGetcolumnvalResponse.ColumnValListForTopVo result = rsp.getResult();
                List<OapiAttendanceGetcolumnvalResponse.ColumnValForTopVo> columnVals = result.getColumnVals();
                return columnVals;
            } else {
                throw new InvokeDingTalkException(rsp.getErrorCode(), rsp.getErrmsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }

    }
}
