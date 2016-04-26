package com.softserveinc.edu.boardgames.web.controller;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softserveinc.edu.boardgames.configuration.ImageConfiguration;
import com.softserveinc.edu.boardgames.persistence.entity.Address;
import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	ImageConfiguration imageConfiguration;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		Address address = new Address();
		model.addAttribute("user", user);
		model.addAttribute("address", address);
		return "registration";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@RequestParam("fileUpload") CommonsMultipartFile fileUpload, 
			@Valid User user,@RequestParam("confirmPassword") String confirmPassword, 
			BindingResult result, ModelMap model) throws Exception {

		if (result.hasErrors()) {
			return "registration";
		}

		if (userService.isExistsWithUsername(user.getUsername())) {

			FieldError usernameError = new FieldError("user", "username",
					"Sorry, but this usernmae is already taken. Choose another one");
			result.addError(usernameError);
			return "registration";
		}
		
		if (!user.getPassword().equals(confirmPassword)) {

			FieldError passwordError = new FieldError("user", "password",
					"Sorry, but You must confirm Your password");
			result.addError(passwordError);
			return "registration";
		}
		
		if (userService.isExistsWithEmail(user.getEmail())) {

			FieldError emailError = new FieldError("user", "email",
					"Sorry, but this email is already in use!");
			result.addError(emailError);
			return "registration";
		}
		
		if (!validate(user.getEmail())) {

			FieldError emailError = new FieldError("user", "email",
					"Sorry, but Your email seems to be wrong");
			result.addError(emailError);
			return "registration";
		}
		

		userService.createUser(user);
		Image image = new Image();
		image.setUser(user);
		image.setUrl(imageConfiguration.getAvatarUrl(user));
		image.setImageLocation(imageConfiguration.getAvatarPackage(user));
		imageService.create(image);
		String saveDirectory = image.getImageLocation();
		if (fileUpload != null) {					
				fileUpload.transferTo(new File(saveDirectory));
		}

		return "index";
	}

	private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}

	
}