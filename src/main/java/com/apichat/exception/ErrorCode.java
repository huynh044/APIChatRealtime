package com.apichat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum ErrorCode {
	UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Key invalid", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 5 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    UNDEFINEPERMISSION(1008,"Undefine permission",HttpStatus.NOT_FOUND),
    UNDEFINEROLE(1009,"Undefine role", HttpStatus.NOT_FOUND), 
    DUPLICATE_PERMISSION(1010,"Permission already exists",HttpStatus.BAD_REQUEST),
    DUPLICATE_ROLE(1011,"Role already exists",HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1012,"Role not existed",HttpStatus.NOT_FOUND),
    ROOM_NOT_EXISTED(1013,"Room not existed", HttpStatus.NOT_FOUND),
    USER_NOT_IN_ROOM(1014,"User not in room", HttpStatus.NOT_FOUND),
    USER_BLOCKED(1015,"User blocked", HttpStatus.LOCKED)
    ;
	
	int code;
	String message;
	HttpStatusCode httpStatusCode;
}
