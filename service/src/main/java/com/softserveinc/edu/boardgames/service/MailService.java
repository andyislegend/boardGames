package com.softserveinc.edu.boardgames.service;

import static org.springframework.ui.velocity.VelocityEngineUtils.mergeTemplateIntoString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.softserveinc.edu.boardgames.service.util.OnRegistrationCompleteEvent;

@Service
@PropertySource("classpath:properties/mail.properties")
public class MailService {

	private final Logger logger = Logger.getLogger(MailService.class);

	@Autowired
	Environment env;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.credentials.username}")
	private String userName;
	
	@Async
	public void sendMailAboutRegistration(final OnRegistrationCompleteEvent event, final String to, final String userName, final String token) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress("boardGamesExchange@gmail.com", "Board's Game Exchange"));

				final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
				SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
				String date = form.format(new Date());
				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", userName);
				templateVariables.put("confirm", confirmationUrl);
				templateVariables.put("date", date);
				String body = mergeTemplateIntoString(velocityEngine,
						"/velocity/templates/registrationConfirmTemplate.vm", "UTF-8", templateVariables);
				message.setText(body, true);
				message.setSubject("Registration Confirmation");
			}

		};
		
		mailSender.send(preparator);
		logger.info("----Message about registration to " + to + " send successful---");
	}

	@Async
	public void sendMailToBannedUser(final String to, final String userName) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress("boardGamesExchange@gmail.com", "Board's Game Exchange"));

				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", userName);
				String body = mergeTemplateIntoString(velocityEngine, "/velocity/templates/userBanTemplate.vm", "UTF-8",
						templateVariables);
				message.setText(body, true);
				message.setSubject("Banning because of negative feedbacks");
			}

		};
		mailSender.send(preparator);
		logger.info("----Message about ban to " + to + " send successful---");
	}
	
	@Async
	public void remindAboutGameReturnTomorrow(final String to, final String username, 
			final String gameName, final String ownerUsername) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress("boardGamesExchange@gmail.com", "Board's Game Exchange"));
				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", username);
				templateVariables.put("game", gameName);
				templateVariables.put("ownerUsername", ownerUsername);
				String body = mergeTemplateIntoString(velocityEngine, 
						"/velocity/templates/gameReturnTomorrowReminder.vm", "UTF-8", templateVariables);
				message.setText(body, true);
				message.setSubject("Reminding about game return");
			}
		};
		mailSender.send(preparator);
		logger.info("----Message remainding " + to + " to give " 
				+ gameName + "to" + ownerUsername + "---");
	}
	
	@Async
	public void remindThatYouAreLate(final String to, final String username, final Integer days,
			final String gameName, final String ownerUsername) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress("boardGamesExchange@gmail.com", "Board's Game Exchange"));
				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", username);
				templateVariables.put("days", days);
				templateVariables.put("game", gameName);
				templateVariables.put("ownerUsername", ownerUsername);
				String body = mergeTemplateIntoString(velocityEngine, 
						"/velocity/templates/gameReturnIsLateReminder.vm", "UTF-8", templateVariables);
				message.setText(body, true);
				message.setSubject("Reminding about game return");
			}
		};
		mailSender.send(preparator);
		logger.info("----Message remainding " + to + " to give " 
				+ gameName + " to " + ownerUsername + " is late for " + days + "---");
	}
}
