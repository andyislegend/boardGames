package com.softserveinc.edu.boardgames.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;
import com.softserveinc.edu.boardgames.service.configuration.CustomUserDetailsService.CustomUserDetails;

/**
 * 
 * @author Andrii Petryk
 *
 */
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final Logger logger = Logger.getLogger(AjaxAuthenticationSuccessHandler.class);
	
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

		String state = null;

		CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
		logger.info(principal.getUserSatus());

		if (principal.getUserSatus().equals(UserStatus.ACTIVE.name())) {
			state = "ACTIVE";
			logger.info("returned DATA: ACTIVE");
			httpServletResponse.getWriter().print(state);
			httpServletResponse.getWriter().flush();
		}
		
		if (principal.getUserSatus().equals(UserStatus.UNDER_VERIFICATION.name())) {
			state = "UNDER_VERIFICATION";
			logger.info("returned DATA: UNDER_VERIFICATION");
			httpServletResponse.getWriter().print(state);
			httpServletResponse.getWriter().flush();
		}
		
		if (principal.getUserSatus().equals(UserStatus.BANNED.name())) {
			state = "BANNED";
			logger.info("returned DATA: BANNED");
			httpServletResponse.getWriter().print(state);
			httpServletResponse.getWriter().flush();
		}
		

	}

}
