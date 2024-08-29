package com.apichat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	String uid;
	String nickname;
	String username;
	String password;
}
