package com.apichat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apichat.entity.ChatRoom;
import com.apichat.entity.User;
import com.apichat.entity.UserChatRoom;
import com.apichat.entity.UserChatRoomId;

@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, UserChatRoomId> {
	Optional<UserChatRoom> findByUserAndChatRoom(User user, ChatRoom chatRoom);
}
