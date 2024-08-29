package com.apichat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.apichat.dto.request.*;
import com.apichat.dto.response.ApiResponse;
import com.apichat.service.ChatMessageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/chat/group")
public class ChatController {
	ChatMessageService chatMessageService;
	
	@MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageRequest request) {
        chatMessageService.sendMessageToUser(request);
    }
	
	@PostMapping
	ApiResponse<?> createGroup(@RequestBody String name){
		chatMessageService.createRoomForGroup(name);
		return ApiResponse.builder()
				.message("Create group successful with name "+name)
				.build();
	}
	
	
	
}
