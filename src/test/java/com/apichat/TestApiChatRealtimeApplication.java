package com.apichat;

import org.springframework.boot.SpringApplication;

public class TestApiChatRealtimeApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiChatRealtimeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
