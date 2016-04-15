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
		
		@Query("SELECT f FROM Friend f WHERE (f.userOne = ?1 OR f.userTwo = ?1) AND f.status.id = 2")
		public List<Friend> findAllFriendByUser(User user);
		
		@Query("SELECT COUNT(f) FROM Friend f WHERE (f.userOne = ?1 OR f.userTwo = ?1) AND f.status.id = 1")
		public Integer findCountNoConsiderFrinds(User user);
		
		@Query("SELECT f FROM Friend f WHERE (f.userOne = ?1 OR f.userTwo = ?1) AND f.status.id = 1")
		public List<Friend> getAllNoConsiderFriendByUser(User user);
}
