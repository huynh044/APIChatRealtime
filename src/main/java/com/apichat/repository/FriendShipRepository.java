package com.apichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apichat.entity.FriendShip;
import com.apichat.entity.FriendshipId;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, FriendshipId> {

}
