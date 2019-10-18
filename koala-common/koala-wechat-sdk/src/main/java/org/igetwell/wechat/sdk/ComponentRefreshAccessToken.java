package org.igetwell.wechat.sdk;

public class ComponentRefreshAccessToken {

    /**
     * 授权方令牌
     */
    private String authorizerAccessToken;
    /**
     * 有效期
     */
    private long expiresIn = -1;

    /**
     * 刷新令牌
     */
    private String authorizerRefreshToken;

}