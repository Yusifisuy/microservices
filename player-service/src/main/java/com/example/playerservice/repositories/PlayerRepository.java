package com.example.playerservice.repositories;

import com.example.playerservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {



}
