package com.softserveinc.edu.boardgames.web.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.localization.LocaleKeys;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * Controller for working with information about users.
 * 
 * @author Volodymyr Terlyha
 *
 */
@RestController
public class UsersController {

	private final Logger logger = Logger.getLogger(UsersController.class);

	@Autowired
	private UserService userService;

	/**
	 * This method returns all users
	 * 
	 * @author Volodymyr Terlyha
	 * 
	 */
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	/**
	 * This method returns userDTO by username, but with only 5 games 
	 * and 5 tournaments
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@RequestMapping(value = { "/getUserDTO" }, method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUserDTOWith5TournamentsAndGames(@RequestParam("username") String username) {
		return userService.getUserDTOWith5TournamentsAndGames(username);
	}

	/**
	 * This method returns user profile to edit or friends profile page
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@RequestMapping(value = { "/getProfile" }, method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUserProfile(@RequestParam("username") String username) {
		return userService.getUserProfile(username, WebUtil.getPrincipalUsername());
	}

	/**
	 * This method returns needed url to users avatar location.
	 * 
	 * @author Volodymyr Terlyha
	 * @param userName
	 *
	 */
	@RequestMapping(value = { "/getUsersAvatar" }, method = RequestMethod.GET)
	@ResponseBody
	public String getUsersAvatarUrl(@RequestParam("username") String username) {
		return userService.getAvatarUrl(username);
	}

	/**
	 * This method updates user avatar by uploading image.
	 * 
	 * @author Volodymyr Terlyha
	 * @param fileUpload
	 *
	 */
	@RequestMapping(value = { "/updateAvatar" }, consumes = "multipart/form-data", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateUsersAvatar(@RequestParam("fileUpload") CommonsMultipartFile fileUpload) {
		try {
			userService.updateAvatar(fileUpload, WebUtil.getPrincipalUsername());
		} catch (IOException e) {
			logger.error(LocaleKeys.IMAGE_UPLOAD_FAILED, e);
			return new ResponseEntity<String>(LocaleKeys.IMAGE_UPLOAD_FAILED, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(LocaleKeys.IMAGE_UPLOAD, HttpStatus.OK);
	}

	/**
	 * This method returns logged in user.
	 * 
	 * @author Volodymyr Terlyha
	 *
	 */
	@RequestMapping(value = { "/getUser" }, method = RequestMethod.GET)
	@ResponseBody
	public User getOneUser() {
		return userService.findOne(WebUtil.getPrincipalUsername());
	}

	/**
	 * This method bans user by username.
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 *
	 */
	@RequestMapping(value = { "/banUser" }, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> banUser(@RequestParam("username") String username) {
		userService.banUserByAdministrator(username);
		return new ResponseEntity<String>(LocaleKeys.USER_BAN, HttpStatus.OK);
	}

	/**
	 * This method unbans user by username.
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 *
	 */
	@RequestMapping(value = { "/unbanUser" }, method = RequestMethod.PUT)
	public ResponseEntity<String> unbanUser(@RequestParam("username") String username) {
		userService.unbanUserByAdministrator(username);
		return new ResponseEntity<String>(LocaleKeys.USER_UNBAN, HttpStatus.OK);
	}

	/**
	 * This method sends the email to administrator with explanations of a user
	 * which was banned.
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 *
	 */
	@RequestMapping(value = { "/sendEmailOfBannedUser" }, method = RequestMethod.POST)
	public ResponseEntity<String> sendEmailOfBannedUser(@RequestParam("letter") String letter) {
		userService.sendLetterOfBannedUser(letter, WebUtil.getPrincipalUsername());
		return new ResponseEntity<String>(LocaleKeys.LETTER_ABOUT_UNBAN_RECEIVED, HttpStatus.OK);
	}
	
	/**
	 * This method sets notification.
	 * 
	 * @param option
	 *
	 */
	@RequestMapping(value = "/setNotification", method = RequestMethod.POST)
	public void setNotification(@RequestBody boolean option) {
		String userName = WebUtil.getPrincipalUsername();
		userService.setNotification(userName, option);
	}
	
	/**
	 * This method gets status of notification.
	 * 
	 * @param option
	 *
	 */
	@RequestMapping(value = "/getStatusOfNotification", method = RequestMethod.GET)
	public boolean getStatusOfNotification() {
		String userName = WebUtil.getPrincipalUsername();
		boolean option = userService.getStatusOfNotification(userName);
		return option;
	}
}