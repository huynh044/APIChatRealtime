package com.apichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichat.entity.InvalidatedToken;

public interface InvalidedTokenRepository extends JpaRepository<InvalidatedToken, String> {

}
