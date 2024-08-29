package com.apichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apichat.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

}
