package com.softserveinc.edu.boardgames.service.Impl;

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

import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.util.OnRegistrationCompleteEvent;

/**
 * 
 * @author Andrii Petryk
 * 
 *         Class is used to create concrete mails and sends them to user
 *
 */
@Service
@PropertySource("classpath:properties/mail.properties")
public class MailServiceImpl implements MailService{
	
	/**
	 * @param BAN_LETTER_SUBJECT
	 *            letter subject about banning
	 */
	private static final String BAN_LETTER_SUBJECT = "Banning because of negative feedbacks";
	
	/**
	 * @param BAN_LETTER_SUBJECT
	 *            letter subject about unbanning
	 */
	private static final String UNBAN_LETTER_SUBJECT = "User asks to unban him. Username - ";

	private final Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.credentials.username}")
	private String messageSender;
	
	@Value("${mail.credentials.admin}")
	private String adminMail;

	/**
	 * 
	 * @param event
	 * @param to
	 * @param userName
	 * @param token
	 * 
	 *            Used to create mail with registration confirmation link inside
	 *            in order to confirm user's registration and verify users mail
	 *            address
	 * 
	 */
	@Async
	public void sendMailAboutRegistration(final OnRegistrationCompleteEvent event, final String to,
			final String userName, final String token) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress(messageSender, env.getProperty("mail.credentials.title")));

				final String confirmationUrl = event.getAppUrl() + env.getProperty("velocity.template.registration.token") + token;
				SimpleDateFormat form = new SimpleDateFormat(env.getProperty("velocity.template.dateformat"));
				String date = form.format(new Date());
				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", userName);
				templateVariables.put("confirm", confirmationUrl);
				templateVariables.put("date", date);
				String body = mergeTemplateIntoString(velocityEngine,
						env.getProperty("velocity.template.registrationMail"), env.getProperty("velocity.template.encoding"), templateVariables);
				message.setText(body, true);
				message.setSubject("Registration Confirmation");
			}

		};

		mailSender.send(preparator);
		logger.info("----Message about registration to " + to + " send successful---");
	}

	@Override
	@Async
	public void sendMailToBannedUser(final String to, final String userName) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress(messageSender, env.getProperty("mail.credentials.title")));
				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", userName);
				String body = mergeTemplateIntoString(velocityEngine, env.getProperty("velocity.template.banUser"),
						env.getProperty("velocity.template.encoding"), templateVariables);
				message.setText(body, true);
				message.setSubject(BAN_LETTER_SUBJECT);
			}
		};
		mailSender.send(preparator);
		logger.info("----Message about ban to " + to + " sent successfully---");
	}

	@Override
	@Async
	public void sendMailByBannedUserToAdministrator(final String userName, final String letter) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(adminMail);
				message.setFrom(new InternetAddress(messageSender, env.getProperty("mail.credentials.title")));
				message.setText(letter, true);
				message.setSubject(UNBAN_LETTER_SUBJECT + userName);
			}
		};
		mailSender.send(preparator);
		logger.info("----Message about unban from " + userName + " sent successfully---");
	}
	
	@Async
	public void sendMailAboutNeedCoutOfParticipants(final String to){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress("boardGamesExchange@gmail.com", "Board's Game Exchange"));

				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", messageSender);
				String body = mergeTemplateIntoString(velocityEngine, "/velocity/templates/userBanTemplate.vm", "UTF-8",
						templateVariables);
				message.setText(body, true);
				message.setSubject("Need count participants of tournament");
			}

		};
		mailSender.send(preparator);
		logger.info("----Message " + to + " send successful---");
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
	
	public void sendMailAboutNotification(final String to, final String messages, final String type , final String userName, final Date date){
		MimeMessagePreparator preparator = new MimeMessagePreparator(){

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				message.setTo(to);
				message.setFrom(new InternetAddress(messageSender, env.getProperty("mail.credentials.title")));

				Map<String, Object> templateVariables = new HashMap<>();
				templateVariables.put("name", userName);
				templateVariables.put("type", type);
				templateVariables.put("message", messages);
				templateVariables.put("date", date.toString());
				String body = mergeTemplateIntoString(velocityEngine,
						env.getProperty("velocity.template.notificationTemplate"), env.getProperty("velocity.template.encoding"), templateVariables);
				message.setText(body, true);
				message.setSubject("Notification");
			}
			
		};
		mailSender.send(preparator);
	}
}
