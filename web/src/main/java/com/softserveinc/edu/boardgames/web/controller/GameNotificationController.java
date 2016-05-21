package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class GameNotificationController {

	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAllConfirmedActions", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> getAllConfirmations() {
		return exchangeService.selectAllConfirmationsForUser(
				userService.getUser(WebUtil.getPrincipalUsername()).getId());
	}
}
