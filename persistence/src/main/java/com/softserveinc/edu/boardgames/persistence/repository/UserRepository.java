package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;
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

	@Query("Select u.state" + " FROM User u" + " WHERE u.username =:username")
	String getUsersState(String username); 

	@Query("Select u.rating" + " FROM User u" + " WHERE u.username =:username")
	String getUsersRating(String username); 

	User findByEmail(String email);
	
	User findByUsername(String username);

	public User findByFirstName(String firstName);

	@Query("Select u FROM User u JOIN u.address address" + " WHERE address.city =:cityName")
	public List<User> findUserByCity(@Param("cityName") String cityName);
	
	@Query("Select u.username FROM User u WHERE u.userRating <=-5")
	public List<String> findUsesrWithNegativeRating();
	
	/*@Query("Select u FROM User u WHERE u.firstName LIKE ?1 AND u.lastName LIKE ?2 OR u.firstName LIKE ?2 AND u.lastName LIKE ?1")
	public List<User> findAllUserByFirstNameAndLastName(String name, String lastName);*/
	
	@Query("Select u FROM User u WHERE (u.firstName LIKE ?1 AND u.lastName LIKE ?2 OR u.firstName LIKE ?2 AND u.lastName LIKE ?1)"
			+ "AND u.username != ?3")
	public List<User> findAllUserByFirstNameAndLastName(String name, String lastName, String userName);
	
	@Query("SELECT u FROM Friend f RIGHT JOIN f.userId u WHERE f.user.username = ?1 AND f.status.id = 2")
	public List<User> findAllFriends(String userName);
	
	@Query("SELECT u FROM Friend f RIGHT JOIN f.user u WHERE f.userId.username = ?1 AND f.status.id = 1")
	public List<User> getAllNoConsiderFriendByUser(String userName);
	
	@Query("Select u.sex FROM User u WHERE u.username = :username")
	public String findUsersSex(@Param("username") String username);

}
