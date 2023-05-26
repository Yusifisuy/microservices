package com.example.playerservice.controller;

import com.example.playerservice.dto.PlayerDto;
import com.example.playerservice.dto.PlayerRequestDto;
import com.example.playerservice.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

    @PostMapping
    public ResponseEntity<PlayerRequestDto> addPlayer(@RequestBody PlayerRequestDto playerRequestDto){
        return ResponseEntity.ok(playerService.addPlayer(playerRequestDto));
    }
}
