package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Query("SELECT n FROM Notification n WHERE (n.userSender.username = ?1 AND n.user.username = ?2) "
			+ "OR (n.userSender.username = ?2 AND n.user.username = ?1) ORDER BY n.date")
	public List<Notification> getAllMessage(String currentUserName, String friendUserName);
	
	@Modifying
	@Query("UPDATE Notification n SET n.statusOfReading = true WHERE "
			+ "n.id = ?1")
	public void changeStatusOfReadingOfMessage(Integer messageId);
	
	@Query("SELECT COUNT(n) FROM Notification n WHERE n.userSender.username = ?1 AND  n.statusOfReading = false AND n.type = 'MESSAGE'")
	public Integer findAllNotReadMessage(String currentUserName);
	
	@Query("SELECT COUNT(n) FROM Notification n WHERE n.userSender.username = ?1 AND n.user.username = ?2 AND  n.statusOfReading = false AND n.type = 'MESSAGE'")
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	
	@Query(value = "SELECT * FROM Notification n JOIN users u ON n.user_sender = u.id "
			+ "JOIN users us ON n.userId = us.id WHERE u.username = ?1 AND us.username = ?2 OR u.username = ?2 AND us.username = ?1 "
			+ "ORDER BY n.date DESC LIMIT 1", nativeQuery = true)
	public Notification getLastMessage(String currentUserName, String friendUserName);
}
