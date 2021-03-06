package org.igetwell.wechat.sdk.api;

import org.apache.http.Header;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpHeaders;

public abstract class BaseAPI {
    protected static final String BASE_URI = "https://api.weixin.qq.com";
    protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
    protected static final String MP_URI = "https://mp.weixin.qq.com";
    protected static final String MCH_URI = "https://api.mch.weixin.qq.com";
    protected static final String OPEN_URI = "https://open.weixin.qq.com";
    protected static final String WIFI_URI = "https://wifi.weixin.qq.com";
    protected static final String UNION_PAY_URI = "https://api.mch.weixin.qq.com";

    protected static Header APPLICATION_JSON = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
    protected static Header APPLICATION_XML = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());

    protected static final String ACCESS_TOKEN = "access_token";
    protected static final String COMPONENT_ACCESS_TOKEN = "component_access_token";
}
