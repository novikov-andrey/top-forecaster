package com.football.topforecaster.service.impl

import com.football.topforecaster.client.ChampionatClient
import com.football.topforecaster.entity.Match
import com.football.topforecaster.properties.ChampionatClientProperties
import com.football.topforecaster.properties.RplProperties
import com.football.topforecaster.repository.MatchCalendarRepository
import com.football.topforecaster.service.CalendarParsingService
import com.football.topforecaster.service.MatchCalendarService
import com.football.topforecaster.service.NetworkService
import mu.KLogging
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Service
class MatchCalendarServiceImpl(
        val championatClient: ChampionatClient,
        val calendarParsingService: CalendarParsingService,
        val matchCalendarRepository: MatchCalendarRepository,
        val rplProperties: RplProperties
) : MatchCalendarService {

    companion object: KLogging()

    @Transactional
    override fun uploadCalendar() {
        clearCalendar()
        getCalendar()
    }

    private fun getCalendar() {
        val calendarDoc = championatClient.getMatchCalendar()
        val matchRows = calendarParsingService.extractMatches(calendarDoc)
        matchRows.forEach { matchRow ->
            processMatch(matchRow)
        }
    }

    private fun clearCalendar() {
        matchCalendarRepository.deleteAll()
    }

    private fun processMatch(matchRow: Element) {
        val match = Match(
                host = calendarParsingService.extractHostTeam(matchRow),
                guest = calendarParsingService.extractGuestTeam(matchRow),
                date = calendarParsingService.extractDate(matchRow),
                startTime = calendarParsingService.extractStartTime(matchRow),
                hostScore = calendarParsingService.extractHostScore(matchRow),
                guestScore = calendarParsingService.extractGuestScore(matchRow),
                round = calendarParsingService.extractRound(matchRow),
                season = rplProperties.currentSeason
        )
        matchCalendarRepository.save(match)
    }

}