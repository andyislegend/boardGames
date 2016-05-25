package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
    JavaMailSender mailSender;

	@InjectMocks
	UserService userService = new UserService();
	
	@InjectMocks
    MailService mailService;


	

	@Test
	public void testFindUser() {
		User user = new User();
		String username = "vterlyha";
		user.setUsername(username);
		when(userRepository.findByUsername(username)).thenReturn(user);
		User result = userService.getUser(username);
		verify(userRepository).findByUsername(username);
		assertEquals(user, result);
	}

	@Test
	public void testFindAll() {
		List<User> userList = createListWithCustomUser();
		when(userRepository.findAll()).thenReturn(userList);
		List<User> result = userService.findAll();
		verify(userRepository).findAll();
		assertEquals(userList, result);
	}

	@Test
	public void testNullReturnIfNoDataFound() {
		List<User> userList = new ArrayList<User>();
		when(userRepository.findAll()).thenReturn(userList);
		List<User> result = userService.findAll();
		verify(userRepository).findAll();
		assertEquals(new ArrayList<User>(), result);
	}
	
	@Test
	public void testSendMailToBannedUser() {
		mailService.sendMailToBannedUser("vterlyha", "vterlyha@gmail.com");
		Mockito.verify(mailSender).send(any(MimeMessagePreparator.class));
	}
	
	private List<User> createListWithCustomUser() {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setFirstName("Volodymyr");
		user.setLastName("Terlyha");
		user.setUsername("vterlyha");
		user.setEmail("vterlyha@gmail.com");
		user.setPassword("$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW");
		user.setUserRating(0);
		userList.add(user);
		return userList;
	}
}