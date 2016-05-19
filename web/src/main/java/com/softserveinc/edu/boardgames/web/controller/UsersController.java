package com.softserveinc.edu.boardgames.web.controller;

import java.io.File;
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

import com.softserveinc.edu.boardgames.configuration.ImageConfiguration;
import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.service.CityService;
import com.softserveinc.edu.boardgames.service.CountryService;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * Controller for receiving all users.
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
	ImageConfiguration imageConfiguration;
	
	

	/**
	 * Returns all users.
	 */
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> userList = userService.findAll();
		return userList;
	}
	
	@RequestMapping(value = {"/getProfile"}, method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@RequestParam("username") String username) {
		if (!username.equals("Logged in user")) {
			User user = userService.findOne(username);
			return user;
		}
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		return user;
	}
	
	@RequestMapping(value = {"/updateUser"}, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setGender(userDTO.getGender());
		user.setAge(userDTO.getAge());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setCountry(countryService.findById(userDTO.getCountryId()));
		user.setCity(cityService.findById(userDTO.getCityId()));
		userService.updateUser(user);
		return new ResponseEntity<String>("Changes saved", HttpStatus.OK);
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
		String avatarUrl = checkGender(username);
		return avatarUrl;
	}
	
	/**
	 * Returns needed url to avatar location.
	 */
	@RequestMapping(value = {"/getAvatar"}, method = RequestMethod.GET)
	@ResponseBody
	public String getLoggedUsersAvatar() {
		String username = WebUtil.getPrincipalUsername();		
		String avatarUrl = checkGender(username);
		return avatarUrl;
	}
	
	private String checkGender(String username) {
		String maleGender = "male";
		String avatarUrl = imageConfiguration.getAvatarUrl(username);
		String imageName = imageService.findImageNameByUsername(username);
		if (imageName == null) {
			if (userService.findUsersGender(username).equals(maleGender)) {
				avatarUrl = imageConfiguration.getDefaultMaleAvatarUrl();
			} else {
				avatarUrl = imageConfiguration.getDefaultFemaleAvatarUrl();
			}
		}
		return avatarUrl;
	}
	
	/**
	 * Updating users avatar
	 */
	@RequestMapping(value = {"/updateAvatar"}, consumes="multipart/form-data", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateUsersAvatar(@RequestParam("fileUpload") CommonsMultipartFile fileUpload)
			throws IOException {
		try {
		if (fileUpload.isEmpty()) {
			return new ResponseEntity<String>("You haven't chosed the file", HttpStatus.CONFLICT);
		}
		User user = userService.findOne(WebUtil.getPrincipalUsername());		
		Image image = new Image();
		image.setUser(user);
		image.setImageName(user.getUsername());
		image.setImageLocation(imageConfiguration.getAvatarPackage(user.getUsername()));
		imageService.create(image);
		String saveDirectory = image.getImageLocation();
		fileUpload.transferTo(new File(saveDirectory));
		
		} catch(IOException e) {
			return new ResponseEntity<String>("Failed to upload image. Try one more time", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Avatar uploaded", HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/getUser"}, method = RequestMethod.GET)
	@ResponseBody
	public User getOneUser() {
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		return user;
	}
}