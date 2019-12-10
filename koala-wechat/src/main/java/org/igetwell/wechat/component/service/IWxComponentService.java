package org.igetwell.wechat.component.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第三方平台全网检测和授权业务
 */
public interface IWxComponentService {

    /**
     * 小程序或者公众号授权给第三方平台授权连接
     */
    String COMPONENT_LOGIN_PAGE_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s&auth_type=%s&biz_appid=%s";

    /**
     * 小程序或者公众号授权给第三方平台
     * 手机端打开授权链接
     */
    String COMPONENT_MOBILE_LOGIN_PAGE_URL = "https://mp.weixin.qq.com/safe/bindcomponent?action=bindcomponent&no_scan=1&auth_type=3&component_appid=%s&pre_auth_code=%s&redirect_uri=%s&auth_type=%s&biz_appid=%s#wechat_redirect";

    /**
     * 获取第三方平台access_token
     * @return
     * @throws Exception
     */
    String getComponentAccessToken() throws Exception;

    /**
     * 是否强制获取第三方平台access_token
     * @param forceRefresh
     * @return
     * @throws Exception
     */
    void getComponentAccessToken(boolean forceRefresh) throws Exception;

    /**
     * 微信开放平台处理授权事件的推送
     * @param request
     * @param nonce
     * @param timestamp
     * @param msgSignature
     * @throws Exception
     */
    void processAuthorizeEvent(HttpServletRequest request, String nonce, String timestamp, String msgSignature) throws Exception;

    /**
     * 全网发布接入检测消息
     * @param request
     * @param response
     * @param nonce
     * @param timestamp
     * @param msgSignature
     * @throws Exception
     */
    void checkNetwork(HttpServletRequest request, HttpServletResponse response, String nonce, String timestamp, String msgSignature) throws Exception;
    /**
     * 开放平台获取预授权码
     * @return
     * @throws Exception
     */
    String getPreAuthCode() throws Exception;
    /**
     * 使用授权码换取公众号的授权信息
     * @param authorizationCode  授权code
     */
    String authorize(String authorizationCode) throws Exception;

    /**
     * 获取/刷新微信公众号接口调用令牌
     * @param appId 授权方appId
     * @param refreshToken
     * @return
     */
    void refreshToken(String appId, String refreshToken) throws Exception;

    /**
     * 获取授权方的帐号基本信息
     * @param componentAppId
     * @param appId
     */
    void getAuthorized(String componentAppId, String appId) throws Exception;

    /**
     * 将小程序和公众号授权给第三方平台 获取预授权链接（网页端预授权）
     * 注：auth_type、biz_appid两个字段互斥。
     */
    String createPreAuthUrl(String redirectURI) throws Exception;

    /**
     * 将小程序和公众号授权给第三方平台 获取预授权链接（网页端预授权）
     * @param redirectUri
     * @param authType 要授权的帐号类型：1则商户点击链接后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。
     *                 如果未指定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
     * @param bizAppid 指定授权唯一的小程序或公众号
     * 注：auth_type、biz_appid两个字段互斥。
     * @return
     */
    String createPreAuthUrl(String redirectUri, String authType, String bizAppid) throws Exception;

    /**
     * 将小程序和公众号授权给第三方平台 获取预授权链接（手机端预授权）
     * 注：auth_type、biz_appid两个字段互斥。
     */
    String getMobilePreAuthUrl(String redirectUri) throws Exception;


    /**
     * 将小程序和公众号授权给第三方平台 获取预授权链接（手机端预授权）
     * @param redirectUri
     * @param authType 要授权的帐号类型：1则商户点击链接后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。
     *                 如果未指定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
     * @param bizAppid 指定授权唯一的小程序或公众号
     * 注：auth_type、biz_appid两个字段互斥。
     * @return
     */
    String getMobilePreAuthUrl(String redirectUri, String authType, String bizAppid) throws Exception;
}
