package com.softserveinc.edu.boardgames.configuration;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private AuthenticationSuccessHandler defaultHandler;

	public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        if ("true".equals(httpServletRequest.getHeader("X-Login-Ajax-call"))) {
            Iterator<? extends GrantedAuthority> authorities = authentication.getAuthorities().iterator();
            String authority = authorities.hasNext() ?
                    authorities.next().getAuthority() : "NO_AUTHORITY";
            httpServletResponse.getWriter().print(authority);
            httpServletResponse.getWriter().flush();
        } else {
            defaultHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }
    }

}
