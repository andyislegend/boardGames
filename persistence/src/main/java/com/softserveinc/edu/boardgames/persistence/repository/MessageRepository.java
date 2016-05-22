package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Message;

/**
 * This interface has methods for work with message
 * 
 * @author Vasyl Bervetskyy
 * 
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	/**
	 * This method for finding all message with your specific friend
	 * 
	 * @param currentUserName it's your username
	 * @param friendUserName it's username of your friend
	 * @return list of messages
	 */
	@Query("SELECT m FROM Message m WHERE (m.currentUser.username = ?1 AND m.friendUser.username = ?2) "
			+ "OR (m.currentUser.username = ?2 AND m.friendUser.username = ?1) ORDER BY m.date")
	public List<Message> getAllMessage(String currentUserName, String friendUserName);
	
	/**
	 * This method change status of message from not read to read
	 * 
	 * @param messageId it's id of message that we want to change
	 */
	@Modifying
	@Query("UPDATE Message m SET m.statusOfReading = true WHERE "
			+ "m.id = ?1")
	public void changeStatusOfReadingOfMessage(Long messageId);
	
	/**
	 * This method for finding amount of all your not read message
	 * 
	 * @param currentUserName it's your username
	 * @return amount of messages
	 */
	@Query("SELECT COUNT(m) FROM Message m WHERE m.friendUser.username = ?1 AND  m.statusOfReading = false")
	public Integer findAllNotReadMessage(String currentUserName);
	
	/**
	 * This method for finding amount of your not read message with your specific friend
	 * 
	 * @param currentUserName it's your username
	 * @param friendUserName it's username of your friend
	 * @return amount of messages
	 */
	@Query("SELECT COUNT(m) FROM Message m WHERE m.friendUser.username = ?1 AND m.currentUser.username = ?2 AND  m.statusOfReading = false")
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	
	/**
	 * This method for finding last written message with your specific friend
	 * 
	 * @param currentUserName it's your username
	 * @param friendUserName it's username of your friend
	 * @return message
	 */
	@Query(value = "SELECT * FROM message m JOIN users u ON m.current_user_id = u.id "
			+ "JOIN users us ON m.friend_user_id = us.id WHERE u.username = ?1 AND us.username = ?2 OR u.username = ?2 AND us.username = ?1 "
			+ "ORDER BY m.date DESC LIMIT 1", nativeQuery = true)
	public Message getLastMessage(String currentUserName, String friendUserName);

}
