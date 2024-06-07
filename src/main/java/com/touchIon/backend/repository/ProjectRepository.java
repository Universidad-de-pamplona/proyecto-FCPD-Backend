package com.touchIon.backend.repository;

import com.touchIon.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProjectRepository  extends  JpaRepository<Project, UUID>{

}
