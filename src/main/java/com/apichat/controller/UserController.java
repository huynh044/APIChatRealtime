package com.apichat.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.apichat.dto.request.*;
import com.apichat.dto.response.ApiResponse;
import com.apichat.dto.response.UserResponse;
import com.apichat.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/chat/user")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
	UserService userService;
	
	@PostMapping("/create")
	ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request){
		return ApiResponse.<UserResponse>builder()
				.results(userService.createUser(request))
				.build();
	}
	
	@GetMapping("/all")
	ApiResponse<List<UserResponse>> getAllUser(){
		return ApiResponse.<List<UserResponse>>builder()
				.results(userService.getAllUser())
				.build();
	}
	
	@PutMapping
	ApiResponse<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest request){
		return ApiResponse.<UserResponse>builder()
				.results(userService.updateUser(request))
				.build();
	}
	@DeleteMapping
	ApiResponse<UserResponse> deleteUser(){
		userService.deleteUser();
		return ApiResponse.<UserResponse>builder()
				.message("Account deleted")
				.build();
	}
	@GetMapping
	ApiResponse<UserResponse> getUser(){
		return ApiResponse.<UserResponse>builder()
				.results(userService.getInfo())
				.build();
	}
}
