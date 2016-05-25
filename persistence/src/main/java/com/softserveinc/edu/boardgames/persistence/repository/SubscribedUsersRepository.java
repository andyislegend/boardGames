package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;

@Repository
public interface SubscribedUsersRepository extends JpaRepository<SubscribedUsers, Integer>{
	
	@Query("SELECT s FROM SubscribedUsers s WHERE s.isNew = true")
	public List<SubscribedUsers> getAllNewUserSubscriber();
	
	@Modifying
	@Query("UPDATE SubscribedUsers s SET s.isNew = false WHERE "
			+ "s.id = ?1")
	public void changeStatusOfState(Integer id);
	
	@Query("SELECT s FROM SubscribedUsers s WHERE s.event =?1 AND s.user =?2")
	public List<SubscribedUsers> getAllUSersSubscribedToEvent(Integer eventId, Integer useruserId);
	
	@Query("SELECT COUNT(s) FROM SubscribedUsers s WHERE s.event.id =?1 AND s.user.id =?2")
	public Integer getCountOfSubscribedUsers(Integer eventId, Integer useruserId);
	
}
