package com.softserveinc.edu.boardgames.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.VerificationToken;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.util.ConvertSetEnumsToListString;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;
import com.softserveinc.edu.boardgames.persistence.repository.VerificationTokenRepository;

/**
 * UserService.class responsible for realization of DB CRUD and other operation
 * upon User Repository
 * 
 */
@Service
@Transactional
public class UserService {

	/**
	 * @param invalid
	 *            is returned when verification token has expired
	 * 
	 */
	private final static String INVALID_TOKEN_MAIL_CONFIRMATION = "invalid";

	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserService() {
	}

	public UserService(UserRepository userRepository) {
	}

	/**
	 * 
	 * @param user
	 *            receive a user-object from controller in order to add it to DB
	 *            as a new user
	 */
	@Transactional
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserRoles role = UserRoles.USER;
		Set<UserRoles> roles = new HashSet<>();
		roles.add(role);
		user.setUserRoles(roles);
		user.setState(UserStatus.UNDER_VERIFICATION.name());
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

	/**
	 * Check if the username with {@code username} exists in database and return
	 * it is true
	 * 
	 * @param username
	 * @return User
	 */
	@Transactional
	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Checks which roles user with {@code username} has
	 * 
	 * @param username
	 * @return List
	 */
	@Transactional
	public List<String> getRoles(String username) {
		return ConvertSetEnumsToListString.convertToListString(userRepository.getRolesByUserName(username));
	}

	@Transactional
	public void updateUser(User user) {
		userRepository.saveAndFlush(user);
		if (user.getUserRating() <= -5 || user.getState().equals(UserStatus.BANNED.name())) {
			mailService.sendMailToBannedUser(user.getEmail(), user.getUsername());
			return;
		}
	}

	/**
	 * Add to User with {@code username} User Role {@code ADMIN}
	 * 
	 * @param username
	 */
	@Transactional
	public void createAdmin(String username) {
		User user = userRepository.findByUsername(username);
		Set<UserRoles> roles = user.getUserRoles();
		UserRoles newRole = UserRoles.ADMIN;
		roles.add(newRole);
		user.setUserRoles(roles);

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
	public List<User> findAllUsersByCity(Integer cityId) {
		return userRepository.findUserByCity(cityId);
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
		String name = nameAndLastName.trim(); // At first we cut whitespace
												// around word
		String lastName = "";
		if (nameAndLastName.indexOf(" ") != -1) { // If in middle of this word
													// we have whitespace we
													// divide it on 2 parts
			name = nameAndLastName.substring(0, nameAndLastName.indexOf(" ")).trim();
			lastName = nameAndLastName.substring(nameAndLastName.indexOf(" "), nameAndLastName.length()).trim();
		}
		name = name.concat("%");
		lastName = lastName.concat("%"); // Add symbol '%' for use LIKE in query
		return userRepository.findAllUserByFirstNameAndLastName(userName, name, lastName);
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
	 *            finding users gender by username
	 */
	public String findUsersGender(String username) {
		return userRepository.findUsersGender(username);
	}

	/**
	 * Creates verification token for registration confirmation for user with
	 * {@code username}
	 * 
	 * @param user
	 * @param token
	 */
	@Transactional
	public void createVerificationTokenForUser(final User user, final String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	/**
	 * Finds User in database who is referred with {@code verificationToken}
	 * 
	 * @param verificationToken
	 * @return User
	 */
	public User getUserByToken(final String verificationToken) {
		final User user = tokenRepository.findByToken(verificationToken).getUser();
		return user;
	}

	/**
	 * Finds token in database be it's name
	 * 
	 * @param VerificationToken
	 * @return VerificationToken
	 */
	public VerificationToken getVerificationToken(final String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	/**
	 * When token is triggered verificate whether it expired or not. If token
	 * wasn't expired and was used set User status to {@code ACTIVE} and delete
	 * token
	 * 
	 * @param token
	 * @return null or INVALID_TOKEN_MAIL_CONFIRMATION
	 */
	@Transactional
	public String validateVerificationToken(String token) {
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
		if (verificationToken == null) {
			return INVALID_TOKEN_MAIL_CONFIRMATION;
		}

		final User user = verificationToken.getUser();
		final Calendar cal = Calendar.getInstance();

		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return INVALID_TOKEN_MAIL_CONFIRMATION;
		}

		user.setState(UserStatus.ACTIVE.name());
		;
		userRepository.save(user);
		tokenRepository.delete(verificationToken);
		return null;
	}
	
	public void removeToken(VerificationToken token) {
		tokenRepository.delete(token);
	}
	
	public List<AllTournamentsDTO> getUserTournamentsByUserName(String username) {
		return userRepository.getUserTournamentsByUserName(username);
	}

	public List<VerificationToken> findAllTokens() { 
		return tokenRepository.findAll();
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public List<UserDTO> getAllUserDTO() {
		return userRepository.getAllUserDTO();
	}
}