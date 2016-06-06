package com.softserveinc.edu.boardgames.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.City;
import com.softserveinc.edu.boardgames.persistence.entity.Country;
import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.VerificationToken;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.UserMapper;
import com.softserveinc.edu.boardgames.persistence.entity.util.ConvertSetEnumsToListString;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserGender;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;
import com.softserveinc.edu.boardgames.persistence.repository.CityRepository;
import com.softserveinc.edu.boardgames.persistence.repository.CountryRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;
import com.softserveinc.edu.boardgames.persistence.repository.ImageRepository;
import com.softserveinc.edu.boardgames.persistence.repository.NotificationRepository;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;
import com.softserveinc.edu.boardgames.persistence.repository.VerificationTokenRepository;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.service.configuration.ImageConfiguration;

/**
 * UserService.class responsible for realization of DB CRUD and other operation
 * upon User Repository
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/**
	 * @param invalid
	 *            is returned when verification token has expired
	 * 
	 */
	private final static String INVALID_TOKEN_MAIL_CONFIRMATION = "invalid";
	
	private final static String VALID_TOKEN_MAIL_CONFIRMATION = "success";
	
	private final static Integer RATING_TO_BAN_USER = -5;
	
	

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ImageConfiguration imageConfiguration;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GameUserRepository gameUserRepository;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private NotificationRepository notifyRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl() {
	}

	public UserServiceImpl(UserRepository userRepository) {
	}
	
	/**
	 * @author Andrii Petryk
	 * @param user
	 *            receive a user-object from controller in order to add it to DB
	 *            as a new user
	 */
	@Override
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
	 * @author Andrii Petryk
	 * @param username
	 *            must not be non {@literal null}
	 * @return {@literal true} if user with {@code username} does exist in
	 *         database, else {@literal false}
	 */
	@Override
	@Transactional
	public boolean isExistsWithUsername(String username) {
		return userRepository.findByUsername(username) != null;
	}

	/**
	 * Check if user with {@code email} exist in database
	 * @author Andrii Petryk
	 * @param email
	 *            must not be non {@literal null}
	 * @return {@literal true} if user with {@code email} does exist in
	 *         database, else {@literal false}
	 */
	@Override
	@Transactional
	public boolean isExistsWithEmail(String email) {
		return userRepository.findByEmail(email) != null;
	}

	/**
	 * Check if the username with {@code username} exists in database and return
	 * it is true
	 * 
	 * @param username
	 * @return User
	 */
	@Override
	@Transactional
	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Checks which roles user with {@code username} has
	 * @author Andrii Petryk
	 * @param username
	 * @return List
	 */
	@Override
	@Transactional
	public List<String> getRoles(String username) {
		return ConvertSetEnumsToListString.convertToListString(userRepository.getRolesByUserName(username));
	}

	

	/**
	 * Add to User with {@code username} User Role {@code ADMIN}
	 * @author Andrii Petryk
	 * @param username
	 */
	@Override
	@Transactional
	public void createAdmin(String username) {
		User user = userRepository.findByUsername(username);
		Set<UserRoles> roles = user.getUserRoles();
		UserRoles newRole = UserRoles.ADMIN;
		roles.add(newRole);
		user.setUserRoles(roles);

		userRepository.save(user);
	}

	@Override
	public User findById(int id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public List<User> findAllFriends(String userName) {
		return userRepository.findAllFriends(userName);
	}
	
	@Override
	public List<User> getAllNoConsiderFriendByUser(String userName) {
		return userRepository.getAllNoConsiderFriendByUser(userName);
	}
	
	@Override
	public void setNotification(String userName, boolean option){
		userRepository.setNotification(userName, option);
	}

	@Override
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
	 * Creates verification token for registration confirmation for user with
	 * {@code username}
	 * @author Andrii Petryk
	 * @param user
	 * @param token
	 */
	@Override
	@Transactional
	public void createVerificationTokenForUser(final User user, final String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	/**
	 * Finds User in database who is referred with {@code verificationToken}
	 * @author Andrii Petryk
	 * @param verificationToken
	 * @return User
	 */
	@Override
	public User getUserByToken(final String verificationToken) {
		final User user = tokenRepository.findByToken(verificationToken).getUser();
		return user;
	}

	/**
	 * Finds token in database be it's name
	 * @author Andrii Petryk
	 * @param VerificationToken
	 * @return VerificationToken
	 */
	@Override
	public VerificationToken getVerificationToken(final String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	/**
	 * When token is triggered verificate whether it expired or not. If token
	 * wasn't expired and was used set User status to {@code ACTIVE} and delete
	 * token
	 * @author Andrii Petryk
	 * @param token
	 * @return null or INVALID_TOKEN_MAIL_CONFIRMATION
	 */
	@Override
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
		userRepository.save(user);
		tokenRepository.delete(verificationToken);
		return VALID_TOKEN_MAIL_CONFIRMATION;
	}
	
	@Override
	@Transactional
	public void removeToken(VerificationToken token) {
		tokenRepository.delete(token);
	}

	@Override
	public List<VerificationToken> findAllTokens() { 
		return tokenRepository.findAll();
	}
	
	@Override
	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	/**
	 * This method gets all users
	 * 
	 * @author Volodymyr Terlyha
	 * 
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * This method updates information about user
	 * 
	 * @author Volodymyr Terlyha
	 * @param user
	 * 
	 */
	@Override
	@Transactional
	public void updateUser(User user) {
		userRepository.saveAndFlush(user);
	}

	/**
	 * This method gets user by username
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * This method automatically bans user
	 * 
	 * @author Volodymyr Terlyha
	 * @param User
	 */
	@Override
	@Transactional
	public void updateUserWithBan(User user) {
		userRepository.saveAndFlush(user);
		if (user.getUserRating() < User.MINIMAL_RATING_FOR_ACTIVE_USER) {
			mailService.sendMailToBannedUser(user.getEmail(), user.getUsername());
		}
	}

	/**
	 * This method updates user information when editing user Profile
	 * 
	 * @author Volodymyr Terlyha
	 * @param UserDTO
	 */
	@Override
	@Transactional
	public void updateUser(UserDTO userDTO, String username) {
		Country country = (userDTO.getCountryId() != NO_COUNTRY_OR_NO_CITY_SELECTED_BY_USER)
				? countryRepository.findOne(userDTO.getCountryId()) : null;
		City city = (userDTO.getCityId() != NO_COUNTRY_OR_NO_CITY_SELECTED_BY_USER)
				? cityRepository.findOne(userDTO.getCityId()) : null;
		User user = findOne(username);
		UserMapper.toEntity(userDTO, user, country, city);
		userRepository.saveAndFlush(user);
	}

	/**
	 * This method for gets user profile or friends profile page
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * @param loggedInUserUsername
	 * 
	 */
	@Override
	@Transactional
	public UserDTO getUserProfile(String username, String loggedInUserUsername) {
		if (!username.equals(CHECK_LOGGED_IN_USERNAME)) {
			return getUserDTO(username);
		}
		return getUserDTO(loggedInUserUsername);
	}

	/**
	 * This method returns url of user avatar. If user doesn't have avatar
	 * method returns url of avatar according to users gender
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@Override
	public String getAvatarUrl(String username) {
		String avatarUrl = imageConfiguration.getAvatarUrl(username);
		if (imageRepository.findImageNameByUsername(username) == null) {
			if (findUsersGender(username).equalsIgnoreCase(UserGender.MALE.name())) {
				avatarUrl = imageConfiguration.getDefaultMaleAvatarUrl();
			} else {
				avatarUrl = imageConfiguration.getDefaultFemaleAvatarUrl();
			}
		}
		return avatarUrl;
	}

	/**
	 * This method uploads user avatar image and creates entity image for this
	 * file
	 * 
	 * @author Volodymyr Terlyha
	 * @param fileUpload
	 * @param username
	 * 
	 */
	@Override
	public void updateAvatar(CommonsMultipartFile fileUpload, String username) throws IOException {
		String savePath = imageConfiguration.getAvatarPackage(username);
		if (imageRepository.findImageNameByUsername(username) != null) {
			fileUpload.transferTo(new File(savePath));
		} else {
			User user = findOne(username);
			Image image = new Image();
			image.setUser(user);
			image.setImageName(user.getUsername());
			image.setImageLocation(savePath);
			imageService.create(image);
			fileUpload.transferTo(new File(savePath));
		}
	}

	/**
	 * This method bans user by administrator
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@Override
	@Transactional
	public void banUserByAdministrator(String username) {
		User user = findOne(username);
		user.setState(UserStatus.BANNED.name());
		mailService.sendMailToBannedUser(user.getEmail(), user.getUsername());
		userRepository.saveAndFlush(user);
	}

	/**
	 * This method for bans user by administrator
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@Override
	@Transactional
	public void unbanUserByAdministrator(String username) {
		User user = findOne(username);
		user.setState(UserStatus.ACTIVE.name());
		if (user.getUserRating() <= RATING_TO_BAN_USER) {
			user.setUserRating(User.MINIMAL_RATING_FOR_ACTIVE_USER);
		}
		userRepository.saveAndFlush(user);
	}

	/**
	 * This method gets user gender by username
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@Override
	public String findUsersGender(String username) {
		return userRepository.findUsersGender(username);
	}

	/**
	 * This method returns userDTO by username
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	private UserDTO getUserDTO(String username) {
		UserDTO userDTO = userRepository.getUserDTO(username);
		userDTO.setUserTournaments(userRepository.getUserTournamentsByUserName(username));
		userDTO.setUserGames(gameUserRepository.getAllGameUserByUsername(username));
		return userDTO;
	}
	
	/**
	 * This method returns userDTO by username with
	 * limit of 5 Games and Tournaments
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@Override
	public UserDTO getUserDTOWith5TournamentsAndGames(String username) {
		Pageable five = new PageRequest(0, 5);
		UserDTO userDTO = userRepository.getUserDTO(username);
		userDTO.setUserTournaments(userRepository.get5UserTournamentsByUserName(username, five));
		userDTO.setUserGames(gameUserRepository.get5GameUserByUsername(username, five));
		return userDTO;
	}
	
	public void saveNotificationOfBannedUser(String message, String username){
		Notification notification = new Notification();
		notification.setDate(new Date());
		notification.setType("MESSAGE");
		notification.setUser(userRepository.findById(1));
		notification.setUserSender(userRepository.findByUsername(username));
		notification.setMessage(message);
		notifyRepo.saveAndFlush(notification);
	}
	
	@Override
	public void sendMessageByBannedUser(String username, String message) {
		
	}

	@Override
	public boolean getStatusOfNotification(String userName) {
		return userRepository.getStatusOfNotification(userName);
	}
}