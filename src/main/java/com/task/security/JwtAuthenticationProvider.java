package com.task.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String authenticationToken = (String) authentication.getCredentials();
        UserPrincipal agilityTokenDetails = jwtService.parseToken(authenticationToken);
        return new JwtAuthentication(agilityTokenDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthentication.class.isAssignableFrom(authentication));
    }
}