package com.example.playerservice.dto


data class TeamDto @JvmOverloads constructor(
    val teamName: String,
    val teamMail: String,
    val country: String,
    val city: String,
    val league: String,
    val coach: String,

    val players: List<Long>? = ArrayList()
) {
}