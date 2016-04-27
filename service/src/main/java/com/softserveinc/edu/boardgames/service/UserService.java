package com.softserveinc.edu.boardgames.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.util.ConvertSetEnumsToListString;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;
//import com.softserveinc.edu.boardgames.service.util.EmailSenderApp;

/**
 * UserService.class responsible for realization of DB CRUD and other operation
 * upon User Repository
 * 
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param user
	 *            receive a user-object from controller in order to add it to DB
	 *            as a new user
	 */
	@Transactional
	public void createUser(User user) {
		mailService.sendMail(user.getEmail(), user.getUsername(), user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserRoles role = UserRoles.USER;
		Set<UserRoles> roles = new HashSet<>();
		roles.add(role);
		user.setUserRoles(roles);
		userRepository.save(user);
	}

	/**
	 * Check if user with {@code username} exist in database
	 *
	 * @param username
	 *            must not be non {@literal null}
	 * @return {@literal true} if user with {@code username} does exist in
	 *         database, else {@literal false}
	 */
	@Transactional
	public boolean isExistsWithUsername(String username) {
		return userRepository.findByUsername(username) != null;
	}

	/**
	 * Check if user with {@code email} exist in database
	 *
	 * @param email
	 *            must not be non {@literal null}
	 * @return {@literal true} if user with {@code email} does exist in
	 *         database, else {@literal false}
	 */
	@Transactional
	public boolean isExistsWithEmail(String email) {
		return userRepository.findByEmail(email) != null;
	}

	/**
	 * 
	 * @param username
	 *            finding user by username
	 */
	@Transactional(readOnly = true)
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		boolean isChanged = false;
		if (username != null && oldPassword != null && newPassword != null) {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				isChanged = passwordEncoder.matches(oldPassword, user.getPassword());
				if (isChanged) {
					user.setPassword(passwordEncoder.encode(newPassword));
					userRepository.save(user);
				}
			}
		}
		return isChanged;
	}

	@Transactional
	public List<User> findByRole(String role) {
		return userRepository.findByUserRoleAllIgnoreCase(UserRoles.valueOf(role)).stream()
				.collect(Collectors.toList());
	}

	@Transactional
	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public List<String> getRoles(String username) {
		return ConvertSetEnumsToListString.convertToListString(userRepository.getRolesByUserName(username));
	}

	@Transactional
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public void createSuperAdminIfNotExists(User user) {
		if (isExistsWithUsername(user.getUsername()) && findByRole("SUPER_ADMIN").isEmpty()) {
			userRepository.save(user);
		}
	}

	@Transactional
	public void createAdmin(String username, String role) {
		User user = userRepository.findByUsername(username);
		List<String> roles = getRoles(username);
		roles.add(role);
		if (role.equals(UserRoles.ADMIN.toString())) {
			user.setUserRoles(ConvertSetEnumsToListString.convertToSetUserRole(roles, UserRoles.class));
		}
		userRepository.save(user);
	}

	@Transactional
	public void createModerator(String username, String role) {
		User user = userRepository.findByUsername(username);
		List<String> roles = getRoles(username);
		roles.add(role);
		if (role.equals(UserRoles.MODERATOR.toString())) {
			user.setUserRoles(ConvertSetEnumsToListString.convertToSetUserRole(roles, UserRoles.class));
		}
		userRepository.save(user);

	}

	@Transactional
	public void createDBA(String username, String role) {
		User user = userRepository.findByUsername(username);
		List<String> roles = getRoles(username);
		roles.add(role);
		if (role.equals(UserRoles.DBA.toString())) {
			user.setUserRoles(ConvertSetEnumsToListString.convertToSetUserRole(roles, UserRoles.class));
		}
		userRepository.save(user);

	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * 
	 * @param cityName
	 *            finding users by cityName
	 */
	@Transactional
	public List<User> findAllUsersByCity(String cityName) {
		return userRepository.findUserByCity(cityName);
	}

	public User findById(int id) {
		return userRepository.findOne(id);
	}

	public List<User> findAllFriends(String userName) {
		return userRepository.findAllFriends(userName);
	}

	public List<User> getAllNoConsiderFriendByUser(String userName) {
		return userRepository.getAllNoConsiderFriendByUser(userName);
	}

	public List<User> findAllUserByFirstNameAndLastName(String nameAndLastName, String userName) {
		String name = nameAndLastName.trim();
		String lastName = "";
		if (nameAndLastName.indexOf(" ") != -1) {
			name = nameAndLastName.substring(0, nameAndLastName.indexOf(" ")).trim();
			lastName = nameAndLastName.substring(nameAndLastName.indexOf(" "), nameAndLastName.length()).trim();
		}
		name = name.concat("%");
		lastName = lastName.concat("%");
		return userRepository.findAllUserByFirstNameAndLastName(name, lastName, userName);
	}

	/**
	 * 
	 * finding users with userRating lower than -5
	 */
	public List<String> findUserWithNeagativeRating() {
		return userRepository.findUsesrWithNegativeRating();
	}

	/**
	 * 
	 * @param username
	 *            finding users sex by username
	 */
	public String findUsersSex(String username) {
		return userRepository.findUsersSex(username);
	}
}