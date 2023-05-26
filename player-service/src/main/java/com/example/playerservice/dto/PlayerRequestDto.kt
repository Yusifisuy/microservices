package com.example.playerservice.dto

data class PlayerRequestDto(
    val name: String,
    val surname: String,
    val age: Int,
    val email: String,
    val nationality: String,
    val teamId: Long,
    val value: Long
)