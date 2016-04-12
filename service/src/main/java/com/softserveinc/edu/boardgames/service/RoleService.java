package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Role;
import com.softserveinc.edu.boardgames.persistence.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Transactional
	public void addRole(Role role) {
		roleRepository.save(role);
	}

	@Transactional
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}
}
