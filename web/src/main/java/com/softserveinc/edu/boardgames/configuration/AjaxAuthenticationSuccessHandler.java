package com.softserveinc.edu.boardgames.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 
 * @author Andrii Petryk
 *
 */
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private AuthenticationSuccessHandler defaultHandler;

	public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
		this.defaultHandler = defaultHandler;
	}

	public AuthenticationSuccessHandler getDefaultHandler() {
		return defaultHandler;
	}

	public void setDefaultHandler(AuthenticationSuccessHandler defaultHandler) {
		this.defaultHandler = defaultHandler;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		String status = authentication.isAuthenticated() ? "200" : "403";
		httpServletResponse.getWriter().print(status);
		httpServletResponse.getWriter().flush();

	}

}
