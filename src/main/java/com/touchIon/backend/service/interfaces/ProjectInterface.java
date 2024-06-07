package com.touchIon.backend.service.interfaces;

import com.touchIon.backend.model.Project;

import java.util.Optional;
import java.util.UUID;


public interface ProjectInterface {
    public Iterable<Project> findAll();
    public Optional<Project> findById(UUID id);
    public Project save(Project user);
    public  void deleteById(UUID id);
}
