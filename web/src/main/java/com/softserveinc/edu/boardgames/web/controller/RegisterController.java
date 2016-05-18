package com.softserveinc.edu.boardgames.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.configuration.ImageConfiguration;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserPasswordDTO;
import com.softserveinc.edu.boardgames.service.ImageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * This conrollers is responsible for add new user and validating field in
 * registration process.
 * 
 * @param username
 *            must be unique.
 * @param email
 *            must be unique and be a valid mail address.
 * @param password
 *            must be equals to @param confirm password
 * 
 * @author Andrii Petryk
 *
 */
@Controller
public class RegisterController {

	@Autowired
	UserService userService;

	@Autowired
	ImageService imageService;

	@Autowired
	ImageConfiguration imageConfiguration;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * @param VALID_EMAIL_ADDRESS_REGEX
	 *            is used to validate the correctness of mail provided by new
	 *            user during registration
	 */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * @param VALID_PSASSWORD_REGEX
	 *            is used to validate the safety of users password
	 */
	public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");

	/**
	 * @param VALID_USERNAME_REGEX
	 *            is used to validate the safety of username
	 */
	public static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[a-zA-z0-9_-]{3,15}");

	@RequestMapping(value = { "/addNewUser" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addNewUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("gender") String gender, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {

		if (username.isEmpty() || gender.isEmpty() || email.isEmpty() || password.isEmpty()) {

			return new ResponseEntity<String>("Fields marked with \"*\" are required. Plese enter valid data.", HttpStatus.CONFLICT);

		}

		if (!validateUsername(username)) {

			return new ResponseEntity<String>("Sorry, but Username must contain from 3 to 15 symbols.",
					HttpStatus.CONFLICT);
		}

		if (userService.isExistsWithUsername(username)) {

			return new ResponseEntity<String>("Sorry, but this Usernmae is already taken. Choose another one!",
					HttpStatus.CONFLICT);

		}

		if (!validateMail(email)) {

			return new ResponseEntity<String>("Please enter a valid email address", HttpStatus.CONFLICT);

		}

		if (userService.isExistsWithEmail(email)) {

			return new ResponseEntity<String>("Sorry, but this email is already in use!", HttpStatus.CONFLICT);

		}

		if (!validatePassword(password)) {

			return new ResponseEntity<String>(
					"Password must contain from 6 to 20 symbols with at least 1 upper case symbol and 1 number",
					HttpStatus.CONFLICT);

		}

		if (!password.equals(confirmPassword)) {

			return new ResponseEntity<String>("Sorry, but You must confirm Your password", HttpStatus.CONFLICT);

		}

		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setGender(gender);
		userService.createUser(newUser);

		return new ResponseEntity<String>("Dear,"+newUser.getUsername()+" We have sent you a message in order to verify Your email and confirm Your registration. After You confirm, You will be able to Sign in.", HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateUserPassword" }, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUserPassword(@RequestBody UserPasswordDTO userPasswordDTO) {
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		String oldPassword = userPasswordDTO.getOldPassword();
		String newPassword = userPasswordDTO.getNewPassword();
		String confirmPassword = userPasswordDTO.getNewPassword();
		if (oldPassword == null || newPassword == null || confirmPassword == null) {
			return new ResponseEntity<String>("Some of your fields are empty", HttpStatus.CONFLICT);
		}
		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			return new ResponseEntity<String>("Sorry, but you typed wrong old password", HttpStatus.CONFLICT);
		}

		if (!validatePassword(newPassword)) {
			return new ResponseEntity<String>("Sorry, but Your password must contain at least one lower case symbol, "
					+ "one Upper case symbol, one number and be from 6 to 20 chars long", HttpStatus.CONFLICT);
		}

		if (!newPassword.equals(confirmPassword)) {
			return new ResponseEntity<String>("Sorry, but You must confirm Your password", HttpStatus.CONFLICT);
		}
		user.setPassword(passwordEncoder.encode(newPassword));
		userService.updateUser(user);
		return new ResponseEntity<String>("Changes saved", HttpStatus.OK);
	}

	private static boolean validateMail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	private static boolean validatePassword(String password) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
		return matcher.find();
	}

	private static boolean validateUsername(String username) {
		Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
		return matcher.find();
	}

	// /**
	// * Answer the request for registration from web
	// *
	// * @param model
	// * @return a registration form which is connected to user-entity object
	// * fields
	// */
	// @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	// public String newUser(ModelMap model) {
	// User user = new User();
	// Address address = new Address();
	// model.addAttribute("user", user);
	// model.addAttribute("address", address);
	// return "registration";
	// }
	//
	// /**
	// * Validate the registration form and saves the user to database.
	// *
	// * @param fileUpload
	// * @param user
	// * @param confirmPassword
	// * @param result
	// * @param model
	// * @return instance of User and transmit it to service layer
	// * @throws Exception
	// */
	// @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	// public String saveUser(@RequestParam("fileUpload") CommonsMultipartFile
	// fileUpload, @Valid User user,
	// @RequestParam("confirmPassword") String confirmPassword, BindingResult
	// result, ModelMap model)
	// throws Exception {
	//
	// if (!validateUsername(user.getUsername())) {
	//
	// FieldError usernameError = new FieldError("user", "username",
	// "Sorry, but Your Username should be at least 3 charters long and no more
	// then 15 chars!");
	// result.addError(usernameError);
	// return "registration";
	// }
	//
	// if (userService.isExistsWithUsername(user.getUsername())) {
	//
	// FieldError usernameError = new FieldError("user", "username",
	// "Sorry, but this usernmae is already taken. Choose another one");
	// result.addError(usernameError);
	// return "registration";
	// }
	//
	// if (!validatePassword(user.getPassword())) {
	//
	// FieldError passwordError = new FieldError("user", "password",
	// "Sorry, but Your password must contain at least one lower case symbol, "
	// + "one Upper case symbol, one number and be from 6 to 20 chars long");
	// result.addError(passwordError);
	// return "registration";
	// }
	//
	// if (!user.getPassword().equals(confirmPassword)) {
	//
	// FieldError passwordError = new FieldError("user", "password", "Sorry, but
	// You must confirm Your password");
	// result.addError(passwordError);
	// return "registration";
	// }
	//
	// if (userService.isExistsWithEmail(user.getEmail())) {
	//
	// FieldError emailError = new FieldError("user", "email", "Sorry, but this
	// email is already in use!");
	// result.addError(emailError);
	// return "registration";
	// }
	//
	// if (!validateMail(user.getEmail())) {
	//
	// FieldError emailError = new FieldError("user", "email", "Sorry, but Your
	// email seems to be wrong");
	// result.addError(emailError);
	// return "registration";
	// }
	//
	// userService.createUser(user);
	//
	// if (!fileUpload.isEmpty()) {
	// Image image = new Image();
	// image.setUser(user);
	// image.setImageName(user.getUsername());
	// image.setImageLocation(imageConfiguration.getAvatarPackage(user.getUsername()));
	// imageService.create(image);
	// String saveDirectory = image.getImageLocation();
	// fileUpload.transferTo(new File(saveDirectory));
	// }
	//
	// return "index";
	// }

}