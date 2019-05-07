package com.football.topforecaster.integration

import com.football.topforecaster.PostgresIT
import com.football.topforecaster.job.MatchCalendarJob
import com.football.topforecaster.properties.ChampionatClientProperties
import com.football.topforecaster.repository.MatchCalendarRepository
import com.football.topforecaster.service.NetworkService
import com.football.topforecaster.util.FileUtils
import com.football.topforecaster.util.completedMatch
import com.football.topforecaster.util.upcomingMatch
import org.jsoup.nodes.Document
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

class MatchCalendarJobIT: PostgresIT() {

    companion object {
        const val CALENDAR_FILE_NAME = "calendar.html"
    }

    @Autowired
    private lateinit var matchCalendarJob: MatchCalendarJob
    @Autowired
    private lateinit var matchCalendarRepository: MatchCalendarRepository
    @Autowired
    private lateinit var championatProperties: ChampionatClientProperties
    @MockBean
    private lateinit var networkService: NetworkService

    private lateinit var calendarDoc: Document


    @Before
    fun init() {
        calendarDoc = FileUtils.loadDocument(CALENDAR_FILE_NAME)
    }

    @Test
    fun `uploadCalendar happy path`() {
        val url = championatProperties.url + championatProperties.rplCalendarLink
        `when`(networkService.getDocument(url, championatProperties.timeout)).thenReturn(calendarDoc)

        matchCalendarJob.uploadCalendar()

        val matches = matchCalendarRepository.findAll()
        assertEquals(2, matches.size)
        assertEquals(completedMatch(), matches[0])
        assertEquals(upcomingMatch(), matches[1])
    }

}
