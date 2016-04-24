package com.softserveinc.edu.boardgames.web.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@Controller
public class FileUploadController {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	UserService userService;
	
	
	
	@RequestMapping(value = {"/uploadJSP"}, method = RequestMethod.GET)
	public String getUploadJSP() {
		return "fotoUploader";
	}
	
	@RequestMapping(value = {"/uploadFile"}, method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("fileUpload") CommonsMultipartFile fileUpload
			) throws Exception {
		Image image = new Image();
		image.setUser(userService.findOne(WebUtil.getPrincipalUsername()));
		image.setUrl("http://localhost/img/avatar/"+fileUpload.getOriginalFilename());
		imageService.create(image);
		String saveDirectory = "d:/USR/www/img/avatar/" + WebUtil.getPrincipalUsername();
		if (fileUpload != null) {					
				fileUpload.transferTo(new File(saveDirectory));
		}
		
		return "rec";
	}
}
