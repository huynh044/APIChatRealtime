package com.apichat.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
	String nickname;
	@Size(min = 8, message = "PASSWORD_INVALID")
	String password;
}
