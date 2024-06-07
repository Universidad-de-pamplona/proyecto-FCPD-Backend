package com.touchIon.backend.service.interfaces;

import com.touchIon.backend.model.Task;

import java.util.Optional;
import java.util.UUID;


public interface TaskInterface {

    public Iterable<Task> findAll();
    public Optional<Task> findById(UUID id);
    public Task save(Task user);
    public  void deleteById(UUID id);

}

