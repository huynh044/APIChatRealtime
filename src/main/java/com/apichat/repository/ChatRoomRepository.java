package com.apichat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apichat.entity.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
	@Query("SELECT c FROM ChatRoom c WHERE EXISTS " +
	           "(SELECT ucr FROM UserChatRoom ucr WHERE ucr.chatRoom = c AND ucr.user.uid = :user1Id) " +
	           "AND EXISTS " +
	           "(SELECT ucr FROM UserChatRoom ucr WHERE ucr.chatRoom = c AND ucr.user.uid = :user2Id)")
	    Optional<ChatRoom> findChatRoomBetweenUsers(@Param("user1Id") String user1Id, @Param("user2Id") String user2Id);
	
}
