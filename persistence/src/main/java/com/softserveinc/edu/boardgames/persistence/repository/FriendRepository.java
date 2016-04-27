package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;

/**
 * This interface has method for work with friends
 * 
 * @author Vasyl Bervetskyy
 * 
 */

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
		
		/**
		 * This method for finding all friend request
		 */
		@Query("SELECT COUNT(f) FROM Friend f WHERE f.userId.username = ?1 AND f.status.id = 1")
		public Integer findCountNoConsiderFrinds(String username);
		
		/**
		 * This method for changing status of friendship to accepted
		 */	
		@Modifying
		@Query("UPDATE Friend f SET f.status.id = 2 WHERE f.user = ?1 AND f.userId = ?2")
		public void changeStatusOfFriendshipToAccepted(User user, User userId);
		
		/**
		 * This method for changing status of friendship to rejected
		 */	
		@Modifying
		@Query("UPDATE Friend f SET f.status.id = 3 WHERE f.user = ?1 AND f.userId = ?2")
		public void changeStatusOfFriendshipToRejected(User user, User userId);
		
}
