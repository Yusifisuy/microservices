package com.example.playerservice.dto

data class PlayerDto (
    val name: String,
    val surname: String,
    val age: Int,
    val email: String,
    val nationality: String,
    val currentTeam: TeamBasicDto,
    val value: Long
)