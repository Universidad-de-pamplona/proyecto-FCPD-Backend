package com.touchIon.backend.service;

import com.touchIon.backend.model.User;
import com.touchIon.backend.repository.UserRepository;
import com.touchIon.backend.service.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return  userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

}
