package com.example.playerservice.service;

import com.example.playerservice.client.TeamServiceClient;
import com.example.playerservice.dto.PlayerDto;
import com.example.playerservice.dto.PlayerRequestDto;
import com.example.playerservice.dto.TeamBasicDto;
import com.example.playerservice.exceptions.PlayerNotFoundException;
import com.example.playerservice.model.Player;
import com.example.playerservice.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    Logger logger = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    private final TeamServiceClient teamServiceClient;

    public PlayerService(PlayerRepository playerRepository, TeamServiceClient teamServiceClient) {
        this.playerRepository = playerRepository;
        this.teamServiceClient = teamServiceClient;
    }

    public PlayerRequestDto addPlayer(PlayerRequestDto playerDto){
        Player player = new Player(playerDto.getName(),playerDto.getSurname()
                ,playerDto.getAge(),playerDto.getEmail(),playerDto.getNationality(),playerDto.getTeamId(),playerDto.getValue());
        player = playerRepository.save(player);
        teamServiceClient.updateTeam(player.getTeamId(),player.getPlayerId());
        logger.info("Player saved to database");
        return playerDto;
    }

    public PlayerDto getPlayer(Long playerId){
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()-> new PlayerNotFoundException("Player not found by Id :" + playerId));
        TeamBasicDto teamBasicDto = teamServiceClient.getTeamBasicInfo(player.getTeamId()).getBody();
        return new PlayerDto(player.getName(),player.getSurname()
                ,player.getAge(),player.getEmail(),
                player.getNationality(),teamBasicDto,player.getValue());
    }

}
