package com.touchIon.backend.service;

import com.touchIon.backend.model.Team;
import com.touchIon.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    @Transactional
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public void deleteById(UUID id) {
        teamRepository.deleteById(id);
    }

}
