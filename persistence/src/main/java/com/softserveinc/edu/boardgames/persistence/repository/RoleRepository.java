package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserveinc.edu.boardgames.persistence.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
