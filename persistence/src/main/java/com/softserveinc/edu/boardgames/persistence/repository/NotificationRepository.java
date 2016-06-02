package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.Date;
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
			+ "n.id, n.type, n.status, n.message, n.user.username, n.date, n.gameUser.id, n.userSender.username) "
			+ "from Notification n "
			+ "where n.user.username = :username "
			+ "and n.type = 'exchange'")
	public List<GameNotificationDTO> getAllGamesNotifications(@Param("username")String username);
	
	@Query("select COUNT(n) from Notification n "
			+ "where n.user.username = :username "
			+ "and n.status = 'UNCHECKED'"
			+ "and n.type = 'exchange'")
	public Integer countOfUncheckedGameNotifications(@Param("username")String username);
	/**
	 * This method for finding all message with your specific friend
	 * 
	 * @param currentUserName it's your username
	 * @param friendUserName it's username of your friend
	 * @return list of messages
	 */
	@Query("SELECT n FROM Notification n WHERE (n.userSender.username = ?1 AND n.user.username = ?2) "
			+ "OR (n.userSender.username = ?2 AND n.user.username = ?1) AND n.type = 'MESSAGE' ORDER BY n.date")
	public List<Notification> getAllMessage(String currentUserName, String friendUserName);
	
	/**
	 * This method change status of message from not read to read
	 * 
	 * @param messageId it's id of message that we want to change
	 */
	@Modifying
	@Query("UPDATE Notification n SET n.statusOfReading = true WHERE "
			+ "n.id = ?1")
	public void changeStatusOfReadingOfMessage(Integer messageId);
	
	/**
	 * This method for finding amount of all your not read message
	 * 
	 * @param currentUserName it's your username
	 * @return amount of messages
	 */
	@Query("SELECT COUNT(n) FROM Notification n WHERE n.userSender.username = ?1 AND  n.statusOfReading = false AND n.type = 'MESSAGE'")
	public Integer findAllNotReadMessage(String currentUserName);
	
	/**
	 * This method for finding amount of your not read message with your specific friend
	 * 
	 * @param currentUserName it's your username
	 * @param friendUserName it's username of your friend
	 * @return amount of messages
	 */
	@Query("SELECT COUNT(n) FROM Notification n WHERE n.userSender.username = ?1 AND n.user.username = ?2 AND  n.statusOfReading = false AND n.type = 'MESSAGE'")
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	
	/**
	 * This method for finding all notification of user
	 * 
	 * @param userName it's your username
	 * @return list of Notification
	 */
	
	@Query(value = "SELECT * FROM notification WHERE (userId =(SELECT id FROM users WHERE username = ?1) "
			+ "OR user_sender = (SELECT id FROM users WHERE username = ?1)) AND (type = 'MESSAGE' OR type = 'NOTIFICATION' OR type = 'EVENT')", nativeQuery = true)
	public List<Notification> getAllNotificationByUserName(String userName);
	
	@Query("SELECT n FROM Notification n WHERE n.user.isNotificated = true AND n.isNotificated = false AND (type = 'MESSAGE' OR type = 'NOTIFICATION' OR type = 'EVENT')")
	public List<Notification> getAllNotification();
	
	@Modifying
	@Query("UPDATE Notification n SET n.isNotificated = true WHERE "
			+ "n.id = ?1")
	public void makeNotificationRead(Integer id);
	
	@Query("select COUNT(n) from Notification n where n.date = :date and n.type='exchange'")
	public Integer countNotificationForSpecificDate(@Param("date")Date date);
}
