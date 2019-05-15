package com.football.topforecaster.service

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.time.LocalDate
import java.time.LocalTime

interface CalendarParsingService {
    fun extractMatches(calendarDoc: Document): List<Element>
    fun extractHostTeam(match: Element): String
    fun extractGuestTeam(match: Element): String
    fun extractDate(match: Element): LocalDate
    fun extractStartTime(match: Element): LocalTime?
    fun extractHostScore(match: Element): Int?
    fun extractGuestScore(match: Element): Int?
    fun extractRound(match: Element): String
}
