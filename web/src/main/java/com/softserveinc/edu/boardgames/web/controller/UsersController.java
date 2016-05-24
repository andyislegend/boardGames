package com.softserveinc.edu.boardgames.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.service.CityService;
import com.softserveinc.edu.boardgames.service.CountryService;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.TournamentService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * Controller for receiving information about users.
 * 
 * @author Volodymyr Terlyha
 *
 */
@Controller
public class UsersController {
	
	

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
	 * Returns all users.
	 */
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	/**
	 * Returns information about one User
	 */
	@RequestMapping(value = {"/getUserDTO"}, method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUserDTO(@RequestParam("username") String username) {
		return userService.getUserDTO(username);
	}
	
	@RequestMapping(value = {"/getProfile"}, method = RequestMethod.GET)
	@ResponseBody
	public User getUserProfile(@RequestParam("username") String username) {
		return userService.getUserProfile(username, WebUtil.getPrincipalUsername());
	}
	
	@RequestMapping(value = {"/updateUser"}, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
		userService.updateUser(userDTO, WebUtil.getPrincipalUsername());
		return new ResponseEntity<String>("CHANGES_SAVED", HttpStatus.OK);
	}
	
	/**
	 * Returns needed url to users avatar location.
	 * 
	 * @param userName
	 *            username of user, who's avatar we want to find
	 */
	@RequestMapping(value = {"/getUsersAvatar"}, method = RequestMethod.GET)
	@ResponseBody
	public String getUsersAvatar(@RequestParam("username") String username) {
		return userService.getAvatarUrl(username);
	}
	
	/**
	 * Updating users avatar
	 */
	@RequestMapping(value = {"/updateAvatar"}, consumes="multipart/form-data", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateUsersAvatar(@RequestParam("fileUpload") CommonsMultipartFile fileUpload) {
		try {
			userService.updateAvatar(fileUpload, WebUtil.getPrincipalUsername());
		} catch(IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("IMAGE_UPLOAD_FAILED", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("IMAGE_UPLOAD", HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/getUser"}, method = RequestMethod.GET)
	@ResponseBody
	public User getOneUser() {
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		return user;
	}
	
	@RequestMapping(value = {"/banUser"}, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> banUser(@RequestParam("username") String username) {
		userService.banUserByAdministrator(username);
		return new ResponseEntity<String>("USER_BAN", HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/unbanUser"}, method = RequestMethod.PUT)
	public ResponseEntity<String> unbanUser(@RequestParam("username") String username) {
		userService.unbanUserByAdministrator(username);
		return new ResponseEntity<String>("USER_UNBAN", HttpStatus.OK);
	}
}