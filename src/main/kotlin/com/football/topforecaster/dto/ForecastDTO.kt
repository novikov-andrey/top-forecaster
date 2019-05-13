package com.football.topforecaster.dto

data class ForecastDTO(
        var matchId: Long,
        var hostScore: Int,
        var guestScore: Int,
        var userDTO: UserDTO
)
