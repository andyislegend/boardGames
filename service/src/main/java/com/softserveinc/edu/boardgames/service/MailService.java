package com.softserveinc.edu.boardgames.service;

import java.util.Date;

import com.softserveinc.edu.boardgames.service.util.OnRegistrationCompleteEvent;

public interface MailService {

	public void sendMailAboutRegistration(final OnRegistrationCompleteEvent event, final String to,
			final String userName, final String token);

	public void sendMailToBannedUser(final String to, final String userName);
	
	public void sendMailAboutNeedCoutOfParticipants(final String to);
	
	public void remindAboutGameReturnTomorrow(final String to, final String username, 
			final String gameName, final String ownerUsername);
	
	public void remindThatYouAreLate(final String to, final String username, final Integer days,
			final String gameName, final String ownerUsername);
	
	public void sendMailAboutNotification(final String to, final String message, final String type ,final String username, final Date date);
	
	public void sendMailByBannedUserToAdministrator(final String userName, final String letter);
}
