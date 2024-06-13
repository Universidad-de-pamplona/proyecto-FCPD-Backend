package com.touchIon.backend.controller;

import com.touchIon.backend.model.Team;
import com.touchIon.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        Iterable<Team> teams = teamService.findAll();
        List<Team> teamList = StreamSupport.stream(teams.spliterator(), false).toList();
        return new ResponseEntity<>(teamList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable UUID id) {
        Optional<Team> teamOptional = teamService.findById(id);
        return teamOptional.map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.save(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable UUID id, @RequestBody Team team) {
        if (teamService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        team.setId(id); // Ensure the ID from the path is set
        Team updatedTeam = teamService.save(team);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
        teamService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
