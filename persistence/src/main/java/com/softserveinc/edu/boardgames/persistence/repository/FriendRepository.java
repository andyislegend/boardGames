package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
		
		
		
		@Query("SELECT COUNT(f) FROM Friend f WHERE f.userId = ?1 AND f.status.id = 1")
		public Integer findCountNoConsiderFrinds(User user);
		
				
		@Modifying
		@Query("UPDATE Friend f SET f.status.id = 2 WHERE f.user = ?1 AND f.userId = ?2")
		public void changeStatusOfFriendshipToAccepted(User user, User userId);
		
		@Modifying
		@Query("UPDATE Friend f SET f.status.id = 3 WHERE f.user = ?1 AND f.userId = ?2")
		public void changeStatusOfFriendshipToRejected(User user, User userId);
}
