package com.softserveinc.edu.boardgames.service.configuration;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 * This class configure mail sender and send different type of letters
 * 
 * @author Andrii Petryk
 *
 */
@Configuration
@PropertySource("classpath:/properties/mail.properties")
public class MailConfig {

	 private final Logger logger = Logger.getLogger(MailConfig.class);
	
	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setDefaultEncoding(MailConstant.DEFAULT_ENCODING);

		mailSender.setHost(env.getProperty(MailConstant.CONFIG_HOST));
		mailSender.setPort(Integer.parseInt(env.getProperty(MailConstant.CONFIG_PORT)));
		mailSender.setProtocol(env.getProperty(MailConstant.CONFIG_PROTOCOL));
		mailSender.setUsername(env.getProperty(MailConstant.CREDENTIAL_USERNAME));
		mailSender.setPassword(env.getProperty(MailConstant.CREDENTIAL_PASSWORD));
		Properties properties = new Properties();
		properties.put("mail.smtp.aut",Boolean.parseBoolean(env.getProperty("mail.config.smtp.auth")));
		properties.put("mail.smtp.starttls.enable",Boolean.parseBoolean(
				env.getProperty("mail.config.smtp.starttls.enable")));
		properties.put("mail.smtp.socketFactory.fallback", "true");
		mailSender.setJavaMailProperties(properties);
		logger.info("---Gmail auth success---");
		return mailSender;
	}

	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		factory.setResourceLoaderPath(env.getProperty("velocity.resource.loader.path"));
		factory.setPreferFileSystemAccess(env.getProperty("velocity.prefer.file.systema.access", Boolean.class, false));
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", env.getProperty("velocity.resource.loader.class"));
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}