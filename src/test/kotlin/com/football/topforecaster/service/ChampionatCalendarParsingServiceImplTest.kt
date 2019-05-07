package com.football.topforecaster.service

import com.football.topforecaster.service.impl.ChampionatCalendarParsingServiceImpl
import com.football.topforecaster.util.FileUtils
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime


class ChampionatCalendarParsingServiceImplTest {

    companion object {
        const val CALENDAR_FILE_NAME = "calendar.html"
        const val MATCH_FILE_NAME = "match.html"
        const val MATCH_WITH_NO_INFO_FILE_NAME = "match_with_no_score_and_start_time.html"
    }

    private val parsingService = ChampionatCalendarParsingServiceImpl()

    private lateinit var calendarDoc: Document
    private lateinit var matchElement: Element
    private lateinit var matchElementWithNoScoreAndStartTime: Element

    @Before
    fun init() {
        calendarDoc = FileUtils.loadDocument(CALENDAR_FILE_NAME)
        matchElement = FileUtils.loadDocument(MATCH_FILE_NAME)
        matchElementWithNoScoreAndStartTime = FileUtils.loadDocument(MATCH_WITH_NO_INFO_FILE_NAME)
    }

    @Test
    fun `extractMatches happy path`() {
        val matches = parsingService.extractMatches(calendarDoc)
        assertEquals(2, matches.size)
    }

    @Test
    fun `extractHostTeam happy path`() {
        val hostTeam = parsingService.extractHostTeam(matchElement)
        assertEquals("Урал", hostTeam)
    }

    @Test
    fun `extractGuestTeam happy path`() {
        val guestTeam = parsingService.extractGuestTeam(matchElement)
        assertEquals("Анжи", guestTeam)
    }

    @Test
    fun `extractDate happy path`() {
        val date = parsingService.extractDate(matchElement)
        assertEquals(LocalDate.of(2018, 7, 28), date)
    }

    @Test
    fun `extractDate when no time info`() {
        val date = parsingService.extractDate(matchElementWithNoScoreAndStartTime)
        assertEquals(LocalDate.of(2019, 5, 26), date)
    }

    @Test
    fun `extractStartTime happy path`() {
        val startTime = parsingService.extractStartTime(matchElement)
        assertEquals(LocalTime.of(16, 30), startTime)
    }

    @Test
    fun `extractStartTime when no time info`() {
        val startTime = parsingService.extractStartTime(matchElementWithNoScoreAndStartTime)
        assertEquals(null, startTime)
    }

    @Test
    fun `extractHostScore happy path`() {
        val hostScore = parsingService.extractHostScore(matchElement)
        assertEquals(0, hostScore)
    }

    @Test
    fun `extractHostScore when no score info`() {
        val hostScore = parsingService.extractHostScore(matchElementWithNoScoreAndStartTime)
        assertEquals(null, hostScore)
    }

    @Test
    fun `extractGuestScore happy path`() {
        val hostScore = parsingService.extractGuestScore(matchElement)
        assertEquals(1, hostScore)
    }

    @Test
    fun `extractGuestScore when no score info`() {
        val hostScore = parsingService.extractGuestScore(matchElement)
        assertEquals(1, hostScore)
    }

    @Test
    fun `extractRound happy path`() {
        val round = parsingService.extractRound(matchElement)
        assertEquals(1, round)
    }
}
