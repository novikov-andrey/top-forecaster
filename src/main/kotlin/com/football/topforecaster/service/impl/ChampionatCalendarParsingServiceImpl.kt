package com.football.topforecaster.service.impl

import com.football.topforecaster.service.CalendarParsingService
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class ChampionatCalendarParsingServiceImpl: CalendarParsingService {

    companion object {
        const val DATE_TIME_SEPARATOR = " "
        const val SCORE_SEPARATOR = " : "
        const val EMPTY_SCORE_TEMPLATE = "– : –"
        const val DATE_PATTERN = "dd.MM.yyyy"
        const val TIME_PATTERN = "HH:mm"
    }

    override fun extractMatches(calendarDoc: Document): List<Element> =
        calendarDoc
                .select(".stat-results__table")
                .select("tbody")
                .select("tr")

    override fun extractHostTeam(match: Element): String =
            match
                .select(".stat-results__title")
                .select(".stat-results__title-team").first()
                .select(".table-item")
                .select("span").first()
                .ownText()

    override fun extractGuestTeam(match: Element): String =
            match
                .select(".stat-results__title")
                .select(".stat-results__title-team").last()
                .select(".table-item")
                .select("span").first()
                .ownText()

    override fun extractDate(match: Element): LocalDate {
        val dateTimeStr = extractDateTime(match)
        val dateStr = dateTimeStr.split(DATE_TIME_SEPARATOR).first()
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN))
    }

    override fun extractStartTime(match: Element): LocalTime? {
        val dateTimeStr = extractDateTime(match)
        val dateTimeList = dateTimeStr.split(DATE_TIME_SEPARATOR)
        return if (dateTimeList.size > 1) {
            LocalTime.parse(dateTimeList.last(), DateTimeFormatter.ofPattern(TIME_PATTERN))
        } else null
    }

    override fun extractHostScore(match: Element): Int? =
            extractScore(match)?.split(SCORE_SEPARATOR)?.first()?.toInt()


    override fun extractGuestScore(match: Element): Int? =
            extractScore(match)?.split(SCORE_SEPARATOR)?.last()?.toInt()


    override fun extractRound(match: Element): String =
            match.select(".stat-results__tour-num").first().ownText()

    private fun extractDateTime(match: Element): String =
            match.select(".stat-results__date-time").first().ownText().trim()

    private fun extractScore(match: Element): String? {
        val score = match
                .select(".stat-results__count")
                .select(".stat-results__count-main").first()
                ?.ownText()
        return if (score != EMPTY_SCORE_TEMPLATE) score else null
    }
}
