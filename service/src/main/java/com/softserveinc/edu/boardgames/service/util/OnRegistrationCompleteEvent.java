package com.softserveinc.edu.boardgames.service.util;

import org.springframework.context.ApplicationEvent;

import com.softserveinc.edu.boardgames.persistence.entity.User;

/**
 * 
 * @author Andrii Petryk
 * 
 *         Utility class which works in cooperation with
 *         RegistrtionListener.class Is used to create instance of
 *         RegistartionListener. Provides additional information for Listener,
 *         such as current host of application and user, who has been register
 *
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1084995844303191241L;
	
	private final String appUrl;
	private final User user;

	public OnRegistrationCompleteEvent(final User user, final String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public User getUser() {
		return user;
	}

}
