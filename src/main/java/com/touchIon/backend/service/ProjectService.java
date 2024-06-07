package com.touchIon.backend.service;

import com.touchIon.backend.model.Project;
import com.touchIon.backend.repository.ProjectRepository;
import com.touchIon.backend.service.interfaces.ProjectInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService implements ProjectInterface {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(UUID id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }

}
