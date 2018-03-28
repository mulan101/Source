package com.axboot.jeho.security;

import com.axboot.jeho.AXBootSecurityConfig;
import com.axboot.jeho.code.GlobalConstants;
import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.code.ApiStatus;
import com.chequer.axboot.core.utils.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AXBootAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CookieUtils.deleteCookie(request, response, GlobalConstants.ADMIN_AUTH_TOKEN_KEY);
        RequestUtils requestUtils = RequestUtils.of(request);

        ApiResponse apiResponse;

        if (authException instanceof BadCredentialsException) {
            apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, "비밀번호가 일치하지 않습니다.");
        } else if (authException instanceof UsernameNotFoundException) {
            apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, "존재하지 않는 사용자입니다.");
        } else {
            apiResponse = ApiResponse.redirect(ContextUtil.getPagePath(AXBootSecurityConfig.LOGIN_PAGE));
        }

        if (requestUtils.isAjax()) {
            response.setContentType(HttpUtils.getJsonContentType(request));
            response.getWriter().write(JsonUtils.toJson(apiResponse));
            response.getWriter().flush();
        } else {
            response.sendRedirect(ContextUtil.getPagePath(AXBootSecurityConfig.LOGIN_PAGE));
        }
    }
}

