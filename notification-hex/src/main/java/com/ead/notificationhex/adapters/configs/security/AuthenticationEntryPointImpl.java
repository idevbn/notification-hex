package com.ead.notificationhex.adapters.configs.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    final Logger log = LogManager.getLogger(AuthenticationEntryPointImpl.class);

    @Override
    public void commence(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            final AuthenticationException e) throws IOException, ServletException {
        log.error("Unauthorized error: {}", e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
