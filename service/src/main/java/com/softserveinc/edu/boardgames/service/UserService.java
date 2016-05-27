package com.softserveinc.edu.boardgames.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.VerificationToken;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;

public interface UserService {
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
	User getUserProfile(String username, String loggedInUserUsername);
	String getAvatarUrl(String username);
	void updateAvatar(CommonsMultipartFile fileUpload, String username) throws IOException;
	void banUserByAdministrator(String username);
	void unbanUserByAdministrator(String username);
	String findUsersGender(String username);
	UserDTO getUserDTO(String username);

}
