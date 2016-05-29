package com.softserveinc.edu.boardgames.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;
<<<<<<< HEAD
import com.softserveinc.edu.boardgames.service.Impl.UserServiceImpl;
=======
import com.softserveinc.edu.boardgames.service.Impl.MailServiceImpl;
>>>>>>> 4b5c698c094d93e0170a3facaec15bee2a7d9dbd

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
    JavaMailSender mailSender;

	@InjectMocks
	UserServiceImpl userService = new UserServiceImpl();
	
	@InjectMocks
    MailServiceImpl mailService;

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