package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u" + " FROM User u" + " WHERE  :userRole in elements(u.userRoles)")
	Set<User> findByUserRoleAllIgnoreCase(@Param("userRole") UserRoles userRole);

	@Query("SELECT elements(u.userRoles)" + " FROM User u" + " WHERE u.username =:username")
	Set<UserRoles> getRolesByUserName(@Param("username") String username);

	@Query("Select u.state"+" FROM User u"+" WHERE u.username =:username")
	String getUsersState(String username);  // Not sure if will work
	
	@Query("Select u.rating"+" FROM User u"+" WHERE u.username =:username")
	String getUsersRating(String username); // Not sure if will work
	
	
    User findByUsername(String username);
	
}
