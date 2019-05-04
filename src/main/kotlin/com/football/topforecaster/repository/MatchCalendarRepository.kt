package com.football.topforecaster.repository

import com.football.topforecaster.entity.Match
import org.springframework.data.jpa.repository.JpaRepository

interface MatchCalendarRepository: JpaRepository<Match, Long>
