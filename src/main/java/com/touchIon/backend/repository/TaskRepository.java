package com.touchIon.backend.repository;

import com.touchIon.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID>{
}
