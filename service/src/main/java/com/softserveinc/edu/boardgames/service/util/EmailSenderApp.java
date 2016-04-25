package com.softserveinc.edu.boardgames.service.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderApp {

	/**
	 * Please, check and set parameters for the connection to the email-server
	 */
	final static String USERNAME = "boardGamesExchange@gmail.com";
	final static String PASSWORD = "LV179-Java";

	/**
	 * Sender of the mail
	 */
	final static String EMAILFROM = "boardGamesExchange@gmail.com";

	/**
	 * -------------------------------------------------------------------
	 * Method, which allows us to send the mail
	 * 
	 * @param email
	 *            - entered email to send letter
	 */
	
	public static void sendMail(String username, String password,
			String email) {

		/**
		 * Setting for the connection to the mail server
		 */
		Properties properties = System.getProperties();
//		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		/**
		 * Creating the new session in order to connect to server using settings
		 * shown above
		 */
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				});

		try {

			/**
			 * Creating a MimeMessage Object
			 */
			MimeMessage message = new MimeMessage(session);

			/**
			 * Set the author of the message
			 */
			message.setFrom(new InternetAddress(EMAILFROM));

			/**
			 * Message delivery to server
			 */
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email));

			/**
			 * Subject of message
			 */
			message.setSubject("Registration confirmation!");

			/**
			 * Body part of the mail
			 */
			message.setText("Dear "+ username + "!\n" +"Congratz with successful tegistration on boardGames.com " + "\n"
					+ "Your login is: " + "\n" + username + "\n"
					+ "Your password: " + "\n" + password + "\n" + "Additional info: "
					+ "\n" + "Dont tell your password to anyone! And welcome on board!" + "\n"
					+ "----- DONT REPLAY TO THIS MESSAGE! IT'S GENERATED AUTOMATICALLY -----");

			/**
			 * Sending message
			 */
			Transport.send(message);

			System.out
					.println("Message sent without attachment successfully...");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			System.out.println("You have some problems with connection!");
		}

	}
	
}
