package com.apichat.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apichat.dto.request.UserCreationRequest;
import com.apichat.dto.request.UserUpdateRequest;
import com.apichat.dto.response.UserResponse;
import com.apichat.entity.*;
import com.apichat.enums.Roles;
import com.apichat.exception.AppException;
import com.apichat.exception.ErrorCode;
import com.apichat.mapping.UserMapping;
import com.apichat.repository.RoleRepository;
import com.apichat.repository.UserRepository;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

public interface UserService {
	UserResponse createUser(UserCreationRequest request);
	UserResponse updateUser(UserUpdateRequest request);
	UserResponse getInfo();
	void deleteUser();
	List<UserResponse> getAllUser();
}
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService{
	PasswordEncoder encoder;
	UserRepository userRepository;
	RoleRepository roleRepository;
	UserMapping userMapping;
	@Override
	@Transactional
	public UserResponse createUser(UserCreationRequest request) {
		if(userRepository.existsByUsername(request.getUsername()))
			throw new AppException(ErrorCode.USER_EXISTED);
		User user = userMapping.toUser(request);
		user.setPassword(encoder.encode(request.getPassword()));
		HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(Roles.USER.name()).ifPresent(roles::add);
        user.setRoles(roles);
		return userMapping.toUserResponse(userRepository.save(user));
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('USER')")
	public UserResponse updateUser(UserUpdateRequest request) {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		if(!user.getIsDeleted()) {
			if(request.getNickname().equals(""))
				request.setNickname(user.getNickname());	
			if(request.getPassword().equals(""))
				request.setPassword(user.getPassword());
			else
				request.setPassword(encoder.encode(request.getPassword()));
			userMapping.updateUser(user, request);
		}
		
		return userMapping.toUserResponse(userRepository.save(user));
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public UserResponse getInfo() {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		return userMapping.toUserResponse(user);
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('USER')")
	public void deleteUser() {
		var context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		user.setIsDeleted(true);
		
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserResponse> getAllUser() {
		List<User> users = userRepository.findAll();
	    return users.stream()
	                .map(userMapping::toUserResponse)
	                .collect(Collectors.toList());
	}
	
}
