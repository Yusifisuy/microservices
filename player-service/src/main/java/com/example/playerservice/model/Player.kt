package com.example.playerservice.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "players")
data class Player @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val playerId: Long?=null,
    val name: String,
    val surname: String,
    val age: Int,
    @Email
    val email: String,
    val nationality: String,
    val teamId: Long,
    val value: Long
)  {
    constructor() : this(playerId = null ,name = ""
        , surname = "", age = 0
        , email = "", nationality = "",
        teamId = 0, value = 0
    )

}