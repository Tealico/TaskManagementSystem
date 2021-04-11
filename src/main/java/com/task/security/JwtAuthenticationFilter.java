package com.task.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String AUTHORIZATION_SCHEMA = "Bearer";

    private AuthenticationManager authenticationManager;

    private AuthenticationEntryPoint authenticationEntryPoint;



    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException {
        try {
            String authorizationToken = validateRequestAndGetToken(request,response);
            LOGGER.debug("Trying to authenticate user by Token. Token: {}", authorizationToken);
            Authentication authenticationResult = getAuthenticationFromToken(authorizationToken);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResult);
            SecurityContextHolder.setContext(context);
            LOGGER.debug("AuthenticationFilter is passing request down the filter chain");
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            LOGGER.error("Internal authentication service exception", e);
            authenticationEntryPoint.commence(request, response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String validateRequestAndGetToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            response.sendError(400,"Authorization header not found");
        }
        else if (!token.contains(AUTHORIZATION_SCHEMA)) {
            response.sendError(400,"Authorization schema not found");
        }
        else  token = token.substring(AUTHORIZATION_SCHEMA.length()).trim();
        return token;
    }

    private Authentication getAuthenticationFromToken(String authenticationToken) {
        Authentication authenticationRequest = new JwtAuthentication(authenticationToken);

        return authenticationManager.authenticate(authenticationRequest);
    }


}