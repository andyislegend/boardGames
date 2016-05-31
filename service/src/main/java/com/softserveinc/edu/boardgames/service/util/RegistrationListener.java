package com.softserveinc.edu.boardgames.service.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.UserService;

/**
 * 
 * @author Andrii Petryk
 * 
 *         Class provides a Listener to an event of Registration of new user.
 *         Sends an e-mail with confirmation link to e-mail provided by User during
 *         registration.
 *
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	UserService userService;

	@Autowired
	MailService mailService;

	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(final OnRegistrationCompleteEvent event) {
		final User user = event.getUser();
		String to = user.getEmail();
		String userName = user.getUsername();
		final String token = UUID.randomUUID().toString();
		userService.createVerificationTokenForUser(user, token);

		mailService.sendMailAboutRegistration(event, to, userName, token);
	}

}
