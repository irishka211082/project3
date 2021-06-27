package com.example.demo.authService.jwt;

import com.example.demo.authService.exception.BadRequestException;
import com.example.demo.authService.model.JwtUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@Log
public class JwtFilter extends GenericFilterBean {
    //    private UserContext userContext;
    private JwtProvider jwtProvider;

    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String url = ((HttpServletRequest) servletRequest).getRequestURL().toString();

        if (url.contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (url.contains("/user")) {
            String authorizationHeaderValue = httpRequest.getHeader(AUTHORIZATION);
            if (Objects.isNull(authorizationHeaderValue)) {
                throw new BadRequestException("Missing authorization header in HttpServletRequest");
            }
            if (!authorizationHeaderValue.contains("Bearer ")) {
                throw new BadRequestException("Incorrect authorization header");
            }

            String jwtToken = authorizationHeaderValue.replace("Bearer ", "");

            JwtUser userFromToken = null;
            try {
                userFromToken = jwtProvider.getUserFromToken(jwtToken);
            } catch (JsonProcessingException ex) {
                throw new BadRequestException("Error of processing jwtToken to json");
            }

            if (!checkIsMethodAllowed(userFromToken, url, httpRequest)) {
                throw new BadRequestException("Method not allowed!");
            }
            // add to context our user to servletRequest
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean checkIsMethodAllowed(JwtUser jwtUser, String url, HttpServletRequest httpServletRequest) {

        if (url.contains("/users") && jwtUser.getRole().equals("ADMIN")) {
            return false;
        }
        if (url.contains("/user/{") && jwtUser.getRole().equals("USER")) {
            final Map<String, String> pathVariables =
                    (Map) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            return pathVariables.get("userId").equals(jwtUser.getUserId());
        }
        return true;
    }
}
