package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
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

	@Query("Select u FROM User u JOIN u.city city WHERE city.id =:cityId")
	public List<User> findUserByCity(@Param("cityId") Integer cityId);
	
	@Query("Select u.username FROM User u WHERE u.userRating <=-5")
	public List<String> findUsesrWithNegativeRating();
	
	/**
	 * This method for finding all users wich is not your friends or 
	 * users who you sent request to be friends.
	 * You can find user by name and last name or at first last name and name, it doesn't matter
	 * 
	 * @author Vasyl Bervetskyy
	 * 
	 * @param userName it's your name
	 * @param name it's name of user that you are finding 
	 * @param last name it's last name of user that you are finding 
	 * @return list of users
	 */	
	@Query("SELECT u FROM User u WHERE u.username != ?1 "
			+ "AND u NOT IN ("
			+ "SELECT u FROM Friend f RIGHT JOIN f.userId u WHERE f.user.username = ?1 AND (f.status.id = 2 OR f.status.id = 1))"
			+ "AND u NOT IN("
			+ "SELECT u FROM Friend f RIGHT JOIN f.user u WHERE f.userId.username = ?1 AND f.status.id = 1)"
			+ "AND ((u.firstName LIKE ?2 AND u.lastName LIKE ?3) OR (u.firstName LIKE ?3 AND u.lastName LIKE ?2))")
	public List<User> findAllUserByFirstNameAndLastName(String userName, String name, String lastName);
	
	/**
	 * This method for finding all your friends
	 * 
	 * @author Vasyl Bervetskyy
	 * 
	 * @param userName
	 * @return list of users
	 */	
	@Query("SELECT u FROM Friend f RIGHT JOIN f.userId u WHERE f.user.username = ?1 AND f.status.id = 2")
	public List<User> findAllFriends(String userName);
	
	/**
	 * This method for finding your all not consider friends.
	 * This users want to be your friends, and they sent you request to be them.
	 * 
	 * @author Vasyl Bervetskyy
	 * 
	 * @param userName
	 * @return list of users
	 */	
	@Query("SELECT u FROM Friend f RIGHT JOIN f.user u WHERE f.userId.username = ?1 AND f.status.id = 1")
	public List<User> getAllNoConsiderFriendByUser(String userName);
	
	@Query("Select u.gender FROM User u WHERE u.username = :username")
	public String findUsersGender(@Param("username") String username);
	
	@Modifying
	@Query("Update User u Set u.firstName = :firstName, u.lastName = :lastName where u.username = :username")
	public void updateUserFirstLastName(@Param("firstName") String firstName, 
			@Param("lastName") String lastName, @Param("username") String username);
	
	@Modifying
	@Query("Update User u Set u.username = :newUsername where u.username = :username")
	public void updateUsername(@Param("newUsername") String newUsername, @Param("username") String username);
	
	@Modifying
	@Query("Update User u Set u.userRating = userRating + :addUserRating where u.username = :username")
	public void updateUserRating(@Param("addUserRating") Integer addUserRating, @Param("username") String username);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
			"(t.id, t.name) from Tournament t Join t.users u where u.username =:username")
	public List<TournamentsDTO> getUserTournamentsByUserName(@Param("username")String username);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO" +
			"(u.id, u.username, u.firstName, u.lastName, u.email, u.gender, u.age, u.phoneNumber, "
			+ "u.country.id, u.city.id, u.country.name, u.city.name, u.userRating) from User u Where u.username = :username")
	public UserDTO getUserDTO(@Param("username")String username);
	
}
