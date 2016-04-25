package com.softserveinc.edu.boardgames.service.configuration;

/**
 * This class configure mail constants which program use in MailConfig.class
 * 
 * @author Andrii Petryk.
 *
 */
public class MailConstant {

	static final String DEFAULT_ENCODING = "UTF-8";
	static final String MAIL = "mail";
	static final String CONFIG = "config";
	static final String MAIL_CONFIG = MAIL + "." + CONFIG;
	static final String CONFIG_HOST = MAIL_CONFIG + "." + "host";
	static final String CONFIG_PORT = MAIL_CONFIG + "." + "port";
	static final String CONFIG_PROTOCOL = MAIL_CONFIG + "." + "protocol";
	static final String CREDENTIAL_USERNAME = "mail.credentials.username";
	static final String CREDENTIAL_PASSWORD = "mail.credentials.password";

}
