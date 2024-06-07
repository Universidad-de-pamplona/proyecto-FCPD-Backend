package com.touchIon.backend.service.interfaces;

import com.touchIon.backend.model.Team;
import java.util.Optional;
import java.util.UUID;

public interface TeamInterface {

    public Iterable<Team> findAll();
    public Optional<Team> findById(UUID id);
    public Team save(Team team);
    public  void deleteById(UUID id);

}
