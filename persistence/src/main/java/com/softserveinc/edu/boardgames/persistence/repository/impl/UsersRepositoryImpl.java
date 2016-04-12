package com.softserveinc.edu.boardgames.persistence.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softserveinc.edu.boardgames.persistence.entity.User;

@Repository
public class UsersRepositoryImpl {

	private JpaRepository<User, Long> jpaRepository;
	
	public List<User> findAllUsers() {
		List<User> userList = jpaRepository.findAll();
		return userList;

	}
}
