package com.example.teamservice.service;

import com.example.teamservice.dto.TeamBasicDto;
import com.example.teamservice.dto.TeamDto;
import com.example.teamservice.exception.TeamNotFoundException;
import com.example.teamservice.model.Team;
import com.example.teamservice.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    Logger logger = LoggerFactory.getLogger(TeamService.class);
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public TeamBasicDto getTeamBasicById(Long id){
        Team team = teamRepository.findById(id).orElseThrow(()->new TeamNotFoundException("Team not found by Id:" + id));
        logger.info("TEAM FOUND TEAM ID IS:" + team.getTeamId());
        return new TeamBasicDto(team.getTeamName(),team.getLeague());
    }

    public TeamDto createTeam(TeamDto teamDto){
        Team team = new Team(teamDto.getTeamName(),teamDto.getTeamMail(),
                teamDto.getCountry(),teamDto.getCity(),teamDto.getLeague(),teamDto.getCoach());

        teamRepository.save(team);
        return teamDto;
    }

    public void updateTeamWhenPlayerCreated(Long teamId,Long playerId){
        Team team = teamRepository.findById(teamId).orElseThrow(()->new TeamNotFoundException("Team not found by id"+ teamId));
        List<Long> playerList = team.getPlayers();
        playerList.add(playerId);
        teamRepository.save(team);
        logger.info("Team updated");
    }
}
