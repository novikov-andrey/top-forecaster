package com.football.topforecaster.integration

import com.football.topforecaster.PostgresIT
import com.football.topforecaster.entity.Match
import com.football.topforecaster.repository.MatchCalendarRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.time.LocalTime


class MatchCalendarRepositoryIT: PostgresIT() {

    @Autowired
    private lateinit var matchCalendarRepository: MatchCalendarRepository

    @Test
    fun `save happy path`() {
        matchCalendarRepository.save(Match(
                host = "Анжи",
                guest = "Урал",
                date = LocalDate.of(2018, 4, 3),
                startTime = LocalTime.of(18, 45),
                hostScore = 0,
                guestScore = 1,
                round = 1,
                season = 2018
        ))

        val matches = matchCalendarRepository.findAll()
        assertEquals(1, matches.size)
    }
}
