package com.softserveinc.edu.boardgames.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.VerificationToken;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;

public interface UserService {
	
	/**
	 * @param CHECK_LOGGED_IN_USERNAME
	 *            is used to verify whether we want to get user profile to edit
	 *            or get friends profile
	 */
	public static final String CHECK_LOGGED_IN_USERNAME = "Logged in user";
	
	/**
	 * @param NO_COUNTRY_OR_NO_CITY_SELECTED_BY_USER
	 *            is used to set Country and City entities for user if
	 *            no city or country is choosed
	 */
	public static final Integer NO_COUNTRY_OR_NO_CITY_SELECTED_BY_USER = 0;
	
	void createUser(User user);
	boolean isExistsWithUsername(String username);
	boolean isExistsWithEmail(String email);
	User findOne(String username);
	List<String> getRoles(String username);
	void createAdmin(String username);
	User findById(int id);
	List<User> findAllFriends(String userName);
	List<User> getAllNoConsiderFriendByUser(String userName);
	List<User> findAllUserByFirstNameAndLastName(String nameAndLastName, String userName);
	void createVerificationTokenForUser(final User user, final String token);
	User getUserByToken(final String verificationToken);
	VerificationToken getVerificationToken(final String VerificationToken);
	String validateVerificationToken(String token);
	void removeToken(VerificationToken token);
	List<VerificationToken> findAllTokens();
	void deleteUser(User user);
	List<User> findAll();
	void updateUser(User user);
	User getUser(String username);
	void updateUserWithBan(User user);
	void updateUser(UserDTO userDTO, String username);
	UserDTO getUserProfile(String username, String loggedInUserUsername);
	String getAvatarUrl(String username);
	void updateAvatar(CommonsMultipartFile fileUpload, String username) throws IOException;
	void banUserByAdministrator(String username);
	void unbanUserByAdministrator(String username);
	String findUsersGender(String username);
	void sendMessageByBannedUser(String username, String message);
	public UserDTO getUserDTOWith5TournamentsAndGames(String username);
	
	public void setNotification(String userName, boolean option);
	
	public boolean getStatusOfNotification(String userName);

}
