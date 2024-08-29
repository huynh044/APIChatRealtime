package com.apichat.configuration;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.apichat.entity.User;
import com.apichat.exception.AppException;
import com.apichat.exception.ErrorCode;
import com.apichat.repository.UserRepository;

import lombok.var;

public class UserHandshakeHandler extends DefaultHandshakeHandler{
	@Autowired
	UserRepository userRepository;
	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
		return new UserPrincipal() {
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return user.getUid();
			}
		};
	}
	
}
