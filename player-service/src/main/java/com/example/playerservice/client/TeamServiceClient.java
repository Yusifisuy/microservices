package com.example.playerservice.client;

import com.example.playerservice.dto.TeamBasicDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "team-service",path = "/api/v1/team")
public interface TeamServiceClient {

    Logger logger = LoggerFactory.getLogger(TeamServiceClient.class);

    @GetMapping("/{id}")
    @CircuitBreaker(name = "getTeamBasicInfo",fallbackMethod = "getTeamBasicInfoFallBack")
    ResponseEntity<TeamBasicDto> getTeamBasicInfo(@PathVariable("id") Long id);

    default ResponseEntity<TeamBasicDto> getTeamBasicInfoFallBack(Long id,Exception exception){
        logger.info("Team info couldn`t find in db :" + id);
        return ResponseEntity.ok(new TeamBasicDto("null","null"));
    }

    @PutMapping("/{teamId}/{playerId}")
    ResponseEntity<String> updateTeam(@PathVariable("teamId") Long teamId,@PathVariable("playerId") Long playerId);

}
