package com.apichat.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectRequest {
	String token;
}
