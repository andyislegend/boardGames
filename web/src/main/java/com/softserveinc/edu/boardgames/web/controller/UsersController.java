package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.dto.UsersAddressDTO;

@Controller
public class UsersController {
	
	@Autowired
	UserService userSevice;
	
	private List<UsersAddressDTO> userAddressList = new ArrayList<UsersAddressDTO>();
	
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UsersAddressDTO> getAllUsers(){
		
		for (User user : userSevice.findAll()){
			UsersAddressDTO userAddressDTO = new UsersAddressDTO();
			userAddressDTO.setUsername(user.getUsername());
			userAddressDTO.setFirstName(user.getFirstName());
			userAddressDTO.setLastName(user.getLastName());
			userAddressDTO.setEmail(user.getEmail());
			userAddressDTO.setPhoneNumber(user.getPhoneNumber());
			userAddressDTO.setCountry(user.getAddress().getCountry());
			userAddressDTO.setCity(user.getAddress().getCity());
			userAddressDTO.setStreet(user.getAddress().getStreet());
			userAddressDTO.setHouseNumber(user.getAddress().getHouseNumber());
			userAddressDTO.setRoomNumber(user.getAddress().getRoomNumber());
			userAddressList.add(userAddressDTO);
		}
		return userAddressList;
	}
	
	
}
