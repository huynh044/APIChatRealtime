package com.apichat.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class FriendshipId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
    String userId;

    @Column(name = "friend_id")
    String friendId;
}
