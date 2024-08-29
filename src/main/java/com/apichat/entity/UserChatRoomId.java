package com.apichat.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@Builder
public class UserChatRoomId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
    String userId;

    @Column(name = "chat_room_id")
    String chatRoomId;
}
