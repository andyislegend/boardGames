package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.configuration.ImageConfiguration;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@Controller
public class GetAvatar {

	@Autowired
	ImageService imageService;
	
	@Autowired
	UserService userService;

	@Autowired
	ImageConfiguration imageConfiguration;

	@RequestMapping(value = {"/getAvatar"}, method = RequestMethod.GET)
	@ResponseBody
	public String getUsersAvatar() {
		String username = WebUtil.getPrincipalUsername();
		System.out.println(username);
		String avatarUrl = imageConfiguration.getAvatarUrl(username);
		String imageName = imageService.findImageNameByUsername(username);
		if (imageName == null) {
			if (userService.findUsersSex(username).equals("male")) {
			avatarUrl = imageConfiguration.getDefaultMaleAvatarUrl();
			} else {
				avatarUrl = imageConfiguration.getDefaultFemaleAvatarUrl();
			}
		}
		return avatarUrl;
	}
}