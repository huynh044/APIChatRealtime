package com.apichat.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.apichat.dto.response.MessageResponse;
import com.apichat.entity.Message;

@Mapper(componentModel = "spring")
public interface MessMapper {
	@Mapping(source = "sender.uid", target = "senderId")
	@Mapping(source = "receiver.uid", target = "recieverId")
	MessageResponse toMessReponse(Message message);
}
