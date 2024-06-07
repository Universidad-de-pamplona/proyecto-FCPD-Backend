package com.touchIon.backend.service.interfaces;

import com.touchIon.backend.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserInterface {

    public Iterable<User> findAll();
    public Optional<User> findById(UUID id);
    public User save(User user);
    public  void deleteById(UUID id);

}
