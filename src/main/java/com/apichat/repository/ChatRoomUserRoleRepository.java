package com.apichat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichat.entity.*;

public interface ChatRoomUserRoleRepository extends JpaRepository<ChatRoomUserRole, ChatRoomUserRoleId> {
	Optional<ChatRoomUserRole> findByUserAndChatRoom(User user, ChatRoom chatRoom);
    List<ChatRoomUserRole> findByChatRoom(ChatRoom chatRoom);
    List<ChatRoomUserRole> findByUser(User user);
}
