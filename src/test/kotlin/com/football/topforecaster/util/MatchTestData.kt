package com.football.topforecaster.util

import com.football.topforecaster.entity.Match
import com.football.topforecaster.entity.enums.Tournament
import java.time.LocalDate
import java.time.LocalTime

fun completedMatch() = Match(
        id = 1,
        host = "Урал",
        guest = "Анжи",
        date = LocalDate.of(2018,7,28),
        startTime = LocalTime.of(16,30),
        hostScore = 0,
        guestScore = 1,
        round = "1",
        season = 2018,
        tournament = Tournament.RPL
)

fun upcomingMatch() = Match(
        id = 2,
        host = "Оренбург",
        guest ="Спартак М",
        date = LocalDate.of(2019,5,26),
        startTime = null,
        hostScore = null,
        guestScore = null,
        round = "30",
        season = 2018,
        tournament = Tournament.RPL
)
