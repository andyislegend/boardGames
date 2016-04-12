package com.softserveinc.edu.boardgames.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void update(User user) {
    	userRepository.saveAndFlush(user);
    }

    public User findByFirstName(String firstName) {
        return userRepository.findByFirstName (firstName);
    }

}
