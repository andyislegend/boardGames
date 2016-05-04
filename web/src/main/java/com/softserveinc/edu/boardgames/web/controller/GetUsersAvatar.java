package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.configuration.ImageConfiguration;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.UserService;

/**
 * Controller for receiving avatar of all users when administrator is looking
 * through their information.
 * 
 * @author Volodymyr Terlyha
 *
 */
@Controller
public class GetUsersAvatar {

	@Autowired
	ImageService imageService;

	@Autowired
	UserService userService;

	@Autowired
	ImageConfiguration imageConfiguration;

	/**
	 * Returns needed url to users avatar location.
	 * 
	 * @param userName
	 *            username of user, who's avatar we want to find
	 */
	@RequestMapping(value = {"/getUsersAvatar"}, method = RequestMethod.GET)
	@ResponseBody
	public String getUsersAvatar(@RequestParam("username") String username) {
		String avatarUrl = imageConfiguration.getAvatarUrl(username);
		String imageName = imageService.findImageNameByUsername(username);
		if (imageName == null) {
			if (userService.findUsersGender(username).equals("male")) {
				avatarUrl = imageConfiguration.getDefaultMaleAvatarUrl();
			} else {
				avatarUrl = imageConfiguration.getDefaultFemaleAvatarUrl();
			}
		}
		return avatarUrl;
	}
}