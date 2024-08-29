package com.apichat.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "message")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = true)
    User receiver;  // Nullable khi là tin nhắn trong phòng

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = true)
    ChatRoom chatRoom;  // Nullable khi là tin nhắn giữa hai người
    
    boolean isDeleted = false;
    
    Instant createdAt;
    Instant updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
