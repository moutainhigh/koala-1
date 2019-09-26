package org.igetwell.system.security.mobile.authentication;

import lombok.extern.slf4j.Slf4j;
import org.igetwell.system.security.SpringSecurityService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private SpringSecurityService springSecurityService;

    public SpringSecurityService getSpringSecurityService() {
        return springSecurityService;
    }

    public void setSpringSecurityService(SpringSecurityService springSecurityService) {
        this.springSecurityService = springSecurityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken= (MobileAuthenticationToken) authentication;
        UserDetails userDetails = springSecurityService.loadUserByUsername((String) mobileAuthenticationToken.getPrincipal());
        if (userDetails == null) {
            log.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.Not Found Mobile");
        }
        //认证通过
        MobileAuthenticationToken authenticationToken=new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        //将之前未认证的请求放进认证后的Token中
        authenticationToken.setDetails(authenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MobileAuthenticationToken.class);
    }
}