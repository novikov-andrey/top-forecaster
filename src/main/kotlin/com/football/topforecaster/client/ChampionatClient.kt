package com.football.topforecaster.client

import com.football.topforecaster.properties.ChampionatClientProperties
import com.football.topforecaster.service.NetworkService
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component

@Component
class ChampionatClient(
        val networkService: NetworkService,
        val properties:  ChampionatClientProperties
) {
    fun getMatchCalendar(): Document {
        return networkService.getDocument(
                url = properties.url + properties.rplCalendarLink,
                timeout = properties.timeout
        )
    }
}
