package com.apichat.dto.request;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
	String nickname;
	@Size(min = 5,message = "USERNAME_INVALID")
	String username;
	@Size(min = 8,message = "PASSWORD_INVALID")
	String password;
}
