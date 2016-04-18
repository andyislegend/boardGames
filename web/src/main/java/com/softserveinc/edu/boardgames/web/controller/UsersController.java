package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.dto.UsersAddressDTO;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class UsersController {
	
	@Autowired
	UserService userSevice;
	
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UsersAddressDTO> getAllUsers(){
		List<UsersAddressDTO> userAddressList = new ArrayList<UsersAddressDTO>();
		for (User user : userSevice.findAll()){
			UsersAddressDTO userAddressDTO = new UsersAddressDTO();
			userAddressDTO.setUsername(user.getUsername());
			userAddressDTO.setFirstName(user.getFirstName());
			userAddressDTO.setLastName(user.getLastName());
			userAddressDTO.setEmail(user.getEmail());
			userAddressDTO.setPhoneNumber(user.getPhoneNumber());
			
			userAddressList.add(userAddressDTO);
		}
		return userAddressList;
	}
	
	@RequestMapping(value = {"/getUsersByCityName"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UsersAddressDTO> getUsersByCityName(@RequestParam("cityName") String cityName){
		List<UsersAddressDTO> userAddressList = new ArrayList<UsersAddressDTO>();
		for (User user : userSevice.findAllUsersByCity(cityName)){
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
