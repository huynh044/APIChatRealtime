package com.apichat.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoomUserRoleId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
    private String chatRoomId;

    // constructors, equals, and hashCode methods
}
