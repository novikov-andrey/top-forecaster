package com.football.topforecaster.service

import com.football.topforecaster.client.ChampionatClient
import com.football.topforecaster.entity.Match
import com.football.topforecaster.properties.RplProperties
import com.football.topforecaster.repository.MatchCalendarRepository
import com.football.topforecaster.service.impl.MatchCalendarServiceImpl
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate
import java.time.LocalTime

@RunWith(MockitoJUnitRunner::class)
class MatchCalendarServiceImplTest {

    @InjectMocks
    private lateinit var matchCalendarService: MatchCalendarServiceImpl
    @Mock
    private lateinit var championatClient: ChampionatClient
    @Mock
    private lateinit var parsingService: CalendarParsingService
    @Mock
    private lateinit var matchCalendarRepository: MatchCalendarRepository
    @Mock
    private lateinit var rplProperties: RplProperties

    @Test
    fun `uploadCalendar happy path`() {
        val matchDoc = Document("test")
        val element = Element("test")
        val hostTeam = "Анжи"
        val guestTeam = "Урал"
        val date = LocalDate.of(2018, 4, 3)
        val startTime = LocalTime.of(18, 45)
        val hostScore = 0
        val guestScore = 1
        val round = 1
        val currentSeason = 2018

        `when`(championatClient.getMatchCalendar()).thenReturn(matchDoc)
        `when`(parsingService.extractMatches(matchDoc)).thenReturn(arrayListOf(element))
        `when`(parsingService.extractHostTeam(element)).thenReturn(hostTeam)
        `when`(parsingService.extractGuestTeam(element)).thenReturn(guestTeam)
        `when`(parsingService.extractDate(element)).thenReturn(date)
        `when`(parsingService.extractStartTime(element)).thenReturn(startTime)
        `when`(parsingService.extractHostScore(element)).thenReturn(hostScore)
        `when`(parsingService.extractGuestScore(element)).thenReturn(guestScore)
        `when`(parsingService.extractRound(element)).thenReturn(round)
        `when`(rplProperties.currentSeason).thenReturn(currentSeason)

        matchCalendarService.uploadCalendar()

        verify(matchCalendarRepository).save(Match(
                host = hostTeam,
                guest = guestTeam,
                date = date,
                startTime = startTime,
                hostScore = hostScore,
                guestScore = guestScore,
                round = round,
                season = currentSeason
        ))
    }
}
