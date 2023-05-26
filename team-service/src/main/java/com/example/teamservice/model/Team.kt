package com.example.teamservice.model

import jakarta.persistence.*

@Entity
@Table(name = "teams")
data class Team @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val teamId: Long?=0,
    val teamName: String,
    val teamMail: String,
    val country: String,
    val city: String,
    val league: String,
    val coach: String,
    @ElementCollection
    val players: List<Long> = ArrayList()
){
    constructor() : this(teamId = 0 ,teamName = ""
        , teamMail = "", country = ""
        , city = "", league = "",
        coach = "", players = emptyList()
    )

}
