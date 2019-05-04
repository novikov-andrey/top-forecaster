package com.football.topforecaster.job

import com.football.topforecaster.service.MatchCalendarService
import com.football.topforecaster.service.impl.MatchCalendarServiceImpl
import mu.KLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MatchCalendarJob(
        val matchCalendarService: MatchCalendarService
) {
    companion object: KLogging()

    @Scheduled(cron = "0 * * * * ?")
    fun getCalendar() {
        try {
            logger.info { "Start loading match calendar" }
            matchCalendarService.uploadCalendar()
            logger.info { "Match calendar is successfully uploaded" }
        } catch (e: Exception) {
            MatchCalendarServiceImpl.logger.error(e) { "Fail to load match calendar" }
        }
    }
}
