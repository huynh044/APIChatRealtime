package com.apichat.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.apichat.dto.request.UserCreationRequest;
import com.apichat.dto.request.UserUpdateRequest;
import com.apichat.dto.response.UserResponse;
import com.apichat.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapping {
	@Mapping(target = "uid", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "chatRooms", ignore = true)
	@Mapping(target = "isDeleted", ignore = true)
	@Mapping(target = "roles", ignore = true)
	User toUser (UserCreationRequest request);
	
	UserResponse toUserResponse(User user);
	
	@Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "username", ignore = true)
	@Mapping(target = "chatRooms", ignore = true)
	@Mapping(target = "isDeleted", ignore = true)
	@Mapping(target = "roles", ignore = true)
	void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
