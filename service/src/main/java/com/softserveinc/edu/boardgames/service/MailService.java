package com.softserveinc.edu.boardgames.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

import static org.springframework.ui.velocity.VelocityEngineUtils.mergeTemplateIntoString;

@Service
@PropertySource("classpath:properties/mail.properties")
public class MailService {

	@Autowired
	Environment env;
	
	@Autowired
    private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.credentials.username}")
	private String userName;

	@Async
    public void sendMail(String to, String userName, String password) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom(new InternetAddress("boardGamesExchange@gmail.com", "Board's Game Exchange"));

                SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
                String date = form.format(new Date());
                Map<String, Object> templateVariables = new HashMap<>();
                templateVariables.put("name", userName);
                templateVariables.put("password", password);
                templateVariables.put("date", date);
                String body = mergeTemplateIntoString(velocityEngine, "/velocity/templates/registrationConfirmTemplate.vm", "UTF-8", templateVariables);
                message.setText(body, true);
                message.setSubject("Registration Confirmation");
            }

			
        };
        mailSender.send(preparator);
        System.out.println("----Message to "+ to + " send successful---");
    }
	
	
}
