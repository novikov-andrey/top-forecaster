package com.football.topforecaster.service

import com.football.topforecaster.entity.Match

interface MatchCalendarService {
    fun uploadCalendar()
    fun findMatchById(matchId: Long): Match
}
