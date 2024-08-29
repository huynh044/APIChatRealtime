package com.apichat.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_chat_rooms")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChatRoom {
	@EmbeddedId
    private UserChatRoomId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("chatRoomId")
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
    
    @Builder.Default
    boolean is_leaved = false;
    
    Instant joinAt;
    
    @PrePersist
    protected void onCreate() {
        joinAt = Instant.now();
    }
}

