package org.igetwell.system.security.mobile.authentication;

import org.igetwell.oauth.security.handler.AuthenticationFailureHandler;
import org.igetwell.oauth.security.handler.AuthenticationSuccessHandler;
import org.igetwell.system.security.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 手机号验证码登录配置入口
 */
@Component
public class MobileAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    //实现类怎么确定？ 自定义的实现？？
    @Autowired
    private SpringSecurityService springSecurityService;

    @Override
    public void configure(HttpSecurity http) {
        MobileAuthenticationFilter filter = new MobileAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setSpringSecurityService(springSecurityService);

        http
                // 注册到AuthenticationManager中去
                .authenticationProvider(mobileAuthenticationProvider)
                // 添加到 UsernamePasswordAuthenticationFilter 之后
                // 貌似所有的入口都是 UsernamePasswordAuthenticationFilter
                // 然后UsernamePasswordAuthenticationFilter的provider不支持这个地址的请求
                // 所以就会落在我们自己的认证过滤器上。完成接下来的认证
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}