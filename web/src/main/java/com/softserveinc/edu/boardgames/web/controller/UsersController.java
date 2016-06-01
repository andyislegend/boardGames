package com.softserveinc.edu.boardgames.web.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.service.CityService;
import com.softserveinc.edu.boardgames.service.CountryService;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.TournamentService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.localization.LanguageKeys;
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
	ImageService imageService;

	@Autowired
	UserService userService;

	@Autowired
	CountryService countryService;

	@Autowired
	CityService cityService;

	@Autowired
	TournamentService tournamentService;

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
	 * This method returns userDTO by username
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@RequestMapping(value = { "/getUserDTO" }, method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUserDTO(@RequestParam("username") String username) {
		return userService.getUserDTO(username);
	}

	/**
	 * This method returns user profile to edit or finding friends profile
	 * page
	 * 
	 * @author Volodymyr Terlyha
	 * @param username
	 * 
	 */
	@RequestMapping(value = { "/getProfile" }, method = RequestMethod.GET)
	@ResponseBody
	public User getUserProfile(@RequestParam("username") String username) {
		return userService.getUserProfile(username, WebUtil.getPrincipalUsername());
	}

	/**
	 * This method updates information about user
	 * 
	 * @author Volodymyr Terlyha
	 * @param userDTO
	 * 
	 */
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
		userService.updateUser(userDTO, WebUtil.getPrincipalUsername());
		return new ResponseEntity<String>(LanguageKeys.CHANGES_SAVED, HttpStatus.OK);
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
			logger.error(LanguageKeys.IMAGE_UPLOAD_FAILED, e);
			return new ResponseEntity<String>(LanguageKeys.IMAGE_UPLOAD_FAILED, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(LanguageKeys.IMAGE_UPLOAD, HttpStatus.OK);
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
		return new ResponseEntity<String>(LanguageKeys.USER_BAN, HttpStatus.OK);
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
		return new ResponseEntity<String>(LanguageKeys.USER_UNBAN, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/setNotification", method = RequestMethod.POST)
	public void setNotification(@RequestBody Boolean option) {
		String userName = WebUtil.getPrincipalUsername();
		userService.setNotification(userName, option);
	}
	
	@RequestMapping(value = "/getStatusOfNotification", method = RequestMethod.GET)
	public boolean getStatusOfNotification() {
		String userName = WebUtil.getPrincipalUsername();
		boolean option = userService.getStatusOfNotification(userName);
		return option;
	}
}