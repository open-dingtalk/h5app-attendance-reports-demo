package com.aliyun.dingtalk.model;

import com.dingtalk.api.response.OapiAttendanceGetcolumnvalResponse;
import lombok.Data;

import java.util.List;

@Data
public class AttendanceStatistics {

    /**
     * 智能考勤统计列ID
     */
    private Long id;

    /**
     * 智能考勤统计列别名
     */
    private String alias;

    /**
     * 智能考勤统计列名称
     */
    private String name;


    private String fixValue;

    /**
     * 智能考勤统计列值
     */
    private List<OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal> columnVals;
}
