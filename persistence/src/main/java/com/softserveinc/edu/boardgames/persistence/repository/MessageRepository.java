package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.softserveinc.edu.boardgames.persistence.entity.Message;
import com.softserveinc.edu.boardgames.persistence.entity.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query("SELECT m FROM Message m WHERE (m.currentUser.username = ?1 AND m.friendUser.username = ?2) "
			+ "OR (m.currentUser.username = ?2 AND m.friendUser.username = ?1) ORDER BY m.date")
	public List<Message> getAllMessage(String currentUserName, String friendUserName);

	@Modifying
	@Query("UPDATE Message m SET m.statusOfReading = true WHERE "
			+ "m.id = ?1")
	public void changeStatusOfReadingOfMessage(Long messageId);
	
	@Query("SELECT COUNT(m) FROM Message m WHERE m.friendUser.username = ?1 AND  m.statusOfReading = false")
	public Integer findAllNotReadMessage(String currentUserName);
	
	@Query("SELECT COUNT(m) FROM Message m WHERE m.friendUser.username = ?1 AND m.currentUser.username = ?2 AND  m.statusOfReading = false")
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	

}
