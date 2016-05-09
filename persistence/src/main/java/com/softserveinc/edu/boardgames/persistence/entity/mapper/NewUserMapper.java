package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.NewUserDTO;

public class NewUserMapper {

	public static User toEntity(NewUserDTO newUser) {

		User userRegistered = new User();
		userRegistered.setUsername(newUser.getUsername());
		userRegistered.setPassword(newUser.getPassword());
		userRegistered.setEmail(newUser.getEmail());
		userRegistered.setGender(newUser.getGender());
		userRegistered.setFirstName(newUser.getFirstName());
		userRegistered.setLastName(newUser.getLastName());
		
		return userRegistered;
	}

}
