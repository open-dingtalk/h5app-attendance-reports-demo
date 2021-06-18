package com.aliyun.dingtalk.service;

import com.dingtalk.api.response.OapiV2UserGetResponse;


/**
 * 用户管理
 */
public interface DingTalkUserService {

    OapiV2UserGetResponse.UserGetResponse getUserInfo(String authCode);
}
