package com.apichat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apichat.dto.request.AuthRequest;
import com.apichat.dto.response.ApiResponse;
import com.apichat.dto.response.AuthResponse;
import com.apichat.service.AuthService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/chat/auth")
public class AuthController {
	AuthService authService;
	
	@PostMapping
	ApiResponse<AuthResponse> login(@RequestBody AuthRequest request){
		return ApiResponse.<AuthResponse>builder()
				.results(authService.Authenticate(request))
				.build();
	}
}
