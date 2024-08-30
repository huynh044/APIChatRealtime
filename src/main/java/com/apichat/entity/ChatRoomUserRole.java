package com.apichat.entity;

import com.apichat.enums.RoleForRoom;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUserRole {
	@EmbeddedId
    private ChatRoomUserRoleId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("chatRoomId")
    private ChatRoom chatRoom;

    @Enumerated(EnumType.STRING)
    private RoleForRoom role;
}
