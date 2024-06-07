package com.touchIon.backend.repository;

import com.touchIon.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface  TeamRepository extends  JpaRepository<Team, UUID>{
}
