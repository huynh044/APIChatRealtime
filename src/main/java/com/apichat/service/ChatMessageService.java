package com.apichat.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.*;

import com.apichat.dto.request.GroupRequest;
import com.apichat.dto.request.MessageRequest;
import com.apichat.dto.response.MessageResponse;
import com.apichat.entity.*;
import com.apichat.enums.RoleForRoom;
import com.apichat.exception.AppException;
import com.apichat.exception.ErrorCode;
import com.apichat.mapping.MessMapper;
import com.apichat.repository.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

public interface ChatMessageService {
	void sendMessageToUser(MessageRequest request);
	ChatRoom createRoomForUser(User sender, User reciever);
	List<MessageResponse> messages(String chatRoomId);
	void createRoomForGroup(String name);
	void addMemberToGroup(GroupRequest request);
	
}
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ChatMessageServiceImpl implements ChatMessageService{
	UserRepository userRepository;
	ChatRoomRepository chatRoomRepository;
	MessageRepository messageRepository;
	UserChatRoomRepository userChatRoomRepository;
	SimpMessagingTemplate simpMessagingTemplate;
	MessMapper messMapper;
	ChatRoomUserRoleRepository chatRoomUserRoleRepository;
	@Override
	public void sendMessageToUser(MessageRequest request) {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		User sender = userRepository.findByUsername(username)
				.orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
		User reciever = userRepository.findByUsername(request.getReceiverId())
				.orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
		ChatRoom chatRoom = chatRoomRepository.findChatRoomBetweenUsers(sender.getUid(), reciever.getUid())
				.orElseGet(()->createRoomForUser(sender, reciever));
		Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setSender(sender);
        message.setContent(request.getContent());
        
        messageRepository.save(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/"+chatRoom.getId(), request.getContent());
        simpMessagingTemplate.convertAndSendToUser(request.getReceiverId(), "topic/messages", request.getContent());
	}

	@Override
	public ChatRoom createRoomForUser(User sender, User reciever) {
		ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName("Private Chat: " + sender.getUsername() + " & " + reciever.getUsername());
        chatRoom.setPrivate(true);

        chatRoom = chatRoomRepository.save(chatRoom);

        UserChatRoom senderChatRoom = UserChatRoom.builder().id(new UserChatRoomId(sender.getUid(), chatRoom.getId())).user(sender).chatRoom(chatRoom).build();
        UserChatRoom recipientChatRoom = UserChatRoom.builder().id(new UserChatRoomId(reciever.getUid(), chatRoom.getId())).user(reciever).chatRoom(chatRoom).build();

        userChatRoomRepository.save(senderChatRoom);
        userChatRoomRepository.save(recipientChatRoom);

        return chatRoom;
	}

	@Override
	public List<MessageResponse> messages(String chatRoomId) {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
	            .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_EXISTED));
	    
	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
	    
	    UserChatRoom userChatRoom = userChatRoomRepository.findByUserAndChatRoom(user, chatRoom)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_IN_ROOM));
	    
	    Set<Message> messages = chatRoom.getMessages();
	    return messages.stream()
	            .map(messMapper::toMessReponse)
	            .collect(Collectors.toList());
	}

	@Override
	public void createRoomForGroup(String name) {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
		Set<User> users = new HashSet<>();
		users.add(user);
		ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setPrivate(false);
        chatRoom.setUsers(users);
        ChatRoomUserRole chatRoomUserRole = new ChatRoomUserRole();
        chatRoomUserRole.setChatRoom(chatRoom);
        chatRoomUserRole.setUser(user);
        chatRoomUserRole.setId(new ChatRoomUserRoleId(user.getUid(), chatRoom.getId()));
        chatRoomUserRole.setRole(RoleForRoom.OWNER);
        chatRoomRepository.save(chatRoom);
        chatRoomUserRoleRepository.save(chatRoomUserRole);
		
	}

	@Override
	public void addMemberToGroup(GroupRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    User currentUser = userRepository.findByUsername(username)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

	    ChatRoom chatRoom = chatRoomRepository.findById(request.getRoom_id())
	            .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_EXISTED));

	    ChatRoomUserRole currentUserRole = chatRoomUserRoleRepository.findByUserAndChatRoom(currentUser, chatRoom)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_IN_ROOM));

	    User newMember = userRepository.findById(request.getUid())
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
	    ChatRoomUserRole newMemberRole = new ChatRoomUserRole(new ChatRoomUserRoleId(newMember.getUid(), chatRoom.getId()), newMember, chatRoom, RoleForRoom.MEMBER);;
	    chatRoomUserRoleRepository.findByUserAndChatRoom(newMember, chatRoom).orElse(chatRoomUserRoleRepository.save(newMemberRole));
	}
}
