package com.softserveinc.edu.boardgames.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserPasswordDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserRegistrationDTO;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.service.util.OnRegistrationCompleteEvent;
import com.softserveinc.edu.boardgames.web.localization.LocaleKeys;
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
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	/**
	 * Represent path to userinfo.jsp which appear after successful registration
	 * confirmation
	 */
	private final static String USERINFO_PAGE = "userinfo";

	/**
	 * @param INVALID_TOKEN_MAIL_CONFIRMATION
	 *            is used to validate verification token
	 * 
	 */
	private final static String INVALID_TOKEN_MAIL_CONFIRMATION = "invalid";

	/**
	 * @param VALID_TOKEN_MAIL_CONFIRMATION
	 *            is used as response after successful verification token
	 *            validation
	 * 
	 */
	private final static String VALID_TOKEN_MAIL_CONFIRMATION = "success";

	/**
	 * @param SUCCESS_TOKEN_VALIDATION
	 *            is a flag to mark successful validation of registration token
	 *            and show message to user
	 */
	private final static String SUCCESS_TOKEN_VALIDATION = "success";

	/**
	 * @param TOKEN_EXPIRED
	 *            is a flag to mark that registration token has expired
	 * 
	 */
	private final static String TOKEN_EXPIRED = "expired";

	/**
	 * @param VALID_EMAIL_ADDRESS_REGEX
	 *            is used to validate the correctness of mail provided by new
	 *            user during registration
	 */
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9.'_-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * @param VALID_PSASSWORD_REGEX
	 *            is used to validate the safety of users password
	 */
	private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");

	/**
	 * @param VALID_USERNAME_REGEX
	 *            is used to validate the safety of username
	 */

	private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[a-zA-z0-9 _@!-]{3,9}");

	/**
	 * @param VALID_USERNAME_REGEX
	 *            is used to validate the safety of username
	 */

	private static final Pattern VALID_FIRST_OR_LAST_NAME = Pattern.compile("^[a-zA-Z'-]{0,30}$");

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param gender
	 * @param username
	 * @param password
	 * @param confirmPassword
	 * @param request
	 * @return ResponseEntity with HttpStatus.CONFLICT or HttpStatus.OK
	 * 
	 *         Controller that validate registration form and return
	 *         HttpStatus.CONFLICT with error message if there is invalid data
	 *         in fields provided by user or return HttpStatus.OK if
	 *         registration was successful
	 */
	@RequestMapping(value = { "/addNewUser" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addNewUser(@RequestBody UserRegistrationDTO userDTO,
			final HttpServletRequest request) {
		if (userDTO.getUsername().isEmpty() || userDTO.getGender().isEmpty() || userDTO.getEmail().isEmpty()
				|| userDTO.getPassword().isEmpty()) {

			return new ResponseEntity<String>(LocaleKeys.REQUIRED_FIELD, HttpStatus.CONFLICT);

		}

		if (!validateUsername(userDTO.getUsername().trim())) {

			return new ResponseEntity<String>(LocaleKeys.INVALID_USERNAME, HttpStatus.CONFLICT);
		}

		if (userService.isExistsWithUsername(userDTO.getUsername().trim())) {

			return new ResponseEntity<String>(LocaleKeys.USERNAME_DUPLICATE, HttpStatus.CONFLICT);

		}

		if (!validateMail(userDTO.getEmail().trim())) {

			return new ResponseEntity<String>(LocaleKeys.INVALID_EMAIL, HttpStatus.CONFLICT);

		}

		if (userService.isExistsWithEmail(userDTO.getEmail().trim())) {

			return new ResponseEntity<String>(LocaleKeys.EMAIL_DUPLICATE, HttpStatus.CONFLICT);

		}

		if (!validatePassword(userDTO.getPassword())) {

			return new ResponseEntity<String>(LocaleKeys.INVALID_PASSWORD, HttpStatus.CONFLICT);
		}

		if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {

			return new ResponseEntity<String>(LocaleKeys.NOT_CONFIRMED_PASSWORD, HttpStatus.CONFLICT);

		}

		if (userDTO.getFirstName() != null) {

			if (!validateFirstNameAndLastName(userDTO.getFirstName().trim())) {

				return new ResponseEntity<String>(LocaleKeys.INVALID_FIRST_OR_LAST_NAME, HttpStatus.CONFLICT);
			}
		}

		if (userDTO.getLastName() != null) {

			if (!validateFirstNameAndLastName(userDTO.getLastName().trim())) {

				return new ResponseEntity<String>(LocaleKeys.INVALID_FIRST_OR_LAST_NAME, HttpStatus.CONFLICT);
			}
		}

		User newUser = new User();
		newUser.setEmail(userDTO.getEmail().trim());
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setUsername(userDTO.getUsername().trim());
		newUser.setPassword(userDTO.getPassword().trim());
		newUser.setGender(userDTO.getGender());
		userService.createUser(newUser);

		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newUser, getAppUrl(request)));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param model
	 * @param token
	 * @return userinfo page
	 * 
	 *         Controller that is used to validate token from confirmation link
	 */
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(Model model, @RequestParam("token") final String token) {
		final String result = userService.validateVerificationToken(token);

		if (result.equals(VALID_TOKEN_MAIL_CONFIRMATION)) {
			model.addAttribute(SUCCESS_TOKEN_VALIDATION, true);
		}

		if (result.equals(INVALID_TOKEN_MAIL_CONFIRMATION)) {
			model.addAttribute(TOKEN_EXPIRED, true);
		}

		return USERINFO_PAGE;
	}

	/**
	 * This method that update user password and return HttpStatus.CONFLICT with
	 * error message if there is invalid data in fields provided by user or
	 * return HttpStatus.OK if all data is correct
	 * 
	 * @author Volodymyr Terlyha
	 * 
	 * @param UserPasswordDTO
	 * @return ResponseEntity with HttpStatus.CONFLICT or HttpStatus.OK
	 * 
	 * 
	 */
	@RequestMapping(value = { "/updateUserPassword" }, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUserPassword(@RequestBody UserPasswordDTO userPasswordDTO) {
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		if (userPasswordDTO.getOldPassword() == null || userPasswordDTO.getNewPassword() == null
				|| userPasswordDTO.getConfirmPassword() == null) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		} else if (!passwordEncoder.matches(userPasswordDTO.getOldPassword(), user.getPassword())) {
			return new ResponseEntity<String>(LocaleKeys.OLD_PASSWORD_ANSWER, HttpStatus.CONFLICT);
		} else if (!validatePassword(userPasswordDTO.getNewPassword())) {
			return new ResponseEntity<String>(LocaleKeys.NEW_PASSWORD_ANSWER, HttpStatus.CONFLICT);
		} else if (!userPasswordDTO.getNewPassword().equals(userPasswordDTO.getConfirmPassword())) {
			return new ResponseEntity<String>(LocaleKeys.CONFIRM_PASSWORD_ANSWER, HttpStatus.CONFLICT);
		}
		user.setPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));
		userService.updateUser(user);
		return new ResponseEntity<String>(LocaleKeys.CHANGES_SAVED, HttpStatus.OK);
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
		if (!validateFirstNameAndLastName(userDTO.getFirstName())
				|| !validateFirstNameAndLastName(userDTO.getLastName())) {
			return new ResponseEntity<String>(LocaleKeys.INVALID_FIRST_OR_LAST_NAME, HttpStatus.CONFLICT);
			/*
			 * } else if (true) { return new
			 * ResponseEntity<String>(LocaleKeys.NEW_PASSWORD_ANSWER,
			 * HttpStatus.CONFLICT);
			 */
		} else {
			userService.updateUser(userDTO, WebUtil.getPrincipalUsername());
			return new ResponseEntity<String>(LocaleKeys.CHANGES_SAVED, HttpStatus.OK);
		}
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

	private static boolean validateFirstNameAndLastName(String firstOrLastName) {
		Matcher matcher = VALID_FIRST_OR_LAST_NAME.matcher(firstOrLastName);
		return matcher.find();
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}