package com.touchIon.backend.repository;

import com.touchIon.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface UserRepository  extends  JpaRepository<User, UUID>{

}
