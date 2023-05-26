package com.example.teamservice.controller;

import com.example.teamservice.dto.TeamBasicDto;
import com.example.teamservice.dto.TeamDto;
import com.example.teamservice.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamService teamService;

    Logger logger = LoggerFactory.getLogger(TeamController.class);

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamBasicDto> getTeamBasicInfo(@PathVariable("id") Long id){
        return ResponseEntity.ok(teamService.getTeamBasicById(id));
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto){
        return ResponseEntity.ok(teamService.createTeam(teamDto));
    }

    @PutMapping("/{teamId}/{playerId}")
    public ResponseEntity<String> updateTeam(@PathVariable("teamId") Long teamId,@PathVariable("playerId") Long playerId){
        logger.info("INSIDE OF UPDATING.... ID IS:" + teamId );
        teamService.updateTeamWhenPlayerCreated(teamId,playerId);
        logger.info("UPDATED");
        return ResponseEntity.ok("OK");
    }
}
