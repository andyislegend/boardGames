package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService = new UserService();

	@Test
	public void testFindAll() {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setFirstName("Volodymyr");
		user.setLastName("Terlyha");
		user.setUsername("vterlyha");
		user.setEmail("vterlyha@gmail.com");
		user.setPassword("$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW");
		user.setUserRating(0);
		userList.add(user);
		when(userRepository.findAll()).thenReturn(userList);
		List<User> result = userService.findAll();
		verify(userRepository).findAll();
		assertEquals(userList, result);
	}

	@Test
	public void testFindUserWithNegativeRating() {
		List<String> list = new ArrayList<String>();
		list.add("vterlyha");
		when(userRepository.findUsesrWithNegativeRating()).thenReturn(list);
		List<String> result = userService.findUserWithNeagativeRating();
		verify(userRepository).findUsesrWithNegativeRating();
		assertEquals(list, result);
	}

	@Test
	public void testNullReturnIfNoDataFound() {
		List<User> userList = new ArrayList<User>();
		when(userRepository.findAll()).thenReturn(userList);
		List<User> result = userService.findAll();
		verify(userRepository).findAll();
		assertEquals(new ArrayList<User>(), result);
	}
}