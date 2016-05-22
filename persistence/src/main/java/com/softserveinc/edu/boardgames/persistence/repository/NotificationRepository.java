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
			+ "n.type, n.status, n.message, n.user.username, n.date) "
			+ "from Notification n "
			+ "where n.user.username = :username")
	public List<GameNotificationDTO> getAllGamesNotifications(@Param("username")String username);
}
