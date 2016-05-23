package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO("
			+ "n.id, n.type, n.status, n.message, n.user.username, n.date, n.gameUser.id, u.username) "
			+ "from Notification n, User u "
			+ "where n.user.username = :username "
			+ "and u.id = n.userInvokerId")
	public List<GameNotificationDTO> getAllGamesNotifications(@Param("username")String username);
	
	@Query("select u.username from Exchange e, User u "
			+ "where e.gameUser.id = :gameId "
			+ "and e.userApplierId = u.id")
	public String getInvokerUsername(@Param("gameId")Integer gameId);
	
	@Query("select COUNT(n) from Notification n "
			+ "where n.user.username = :username "
			+ "and n.status = 'UNCHECKED'")
	public Integer countOfUncheckedNotifications(@Param("username")String username);
}
