package com.football.topforecaster.service

import com.football.topforecaster.client.ChampionatClient
import com.football.topforecaster.properties.RplProperties
import com.football.topforecaster.repository.MatchCalendarRepository
import com.football.topforecaster.service.impl.MatchCalendarServiceImpl
import com.football.topforecaster.util.completedMatch
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

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
        val match = completedMatch().apply { id = null }

        `when`(championatClient.getMatchCalendar()).thenReturn(matchDoc)
        `when`(parsingService.extractMatches(matchDoc)).thenReturn(listOf(element))
        `when`(parsingService.extractHostTeam(element)).thenReturn(match.host)
        `when`(parsingService.extractGuestTeam(element)).thenReturn(match.guest)
        `when`(parsingService.extractDate(element)).thenReturn(match.date)
        `when`(parsingService.extractStartTime(element)).thenReturn(match.startTime)
        `when`(parsingService.extractHostScore(element)).thenReturn(match.hostScore)
        `when`(parsingService.extractGuestScore(element)).thenReturn(match.guestScore)
        `when`(parsingService.extractRound(element)).thenReturn(match.round)
        `when`(rplProperties.currentSeason).thenReturn(match.season)

        matchCalendarService.uploadCalendar()

        verify(matchCalendarRepository).save(match)
    }
}
