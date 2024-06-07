package com.touchIon.backend.service;

import com.touchIon.backend.model.Task;
import com.touchIon.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Task> findById(UUID id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

}
