package com.football.topforecaster.service.impl

import com.football.topforecaster.dto.ForecastDTO
import com.football.topforecaster.entity.Forecast
import com.football.topforecaster.repository.ForecastRepository
import com.football.topforecaster.service.ForecastService
import com.football.topforecaster.service.MatchCalendarService
import com.football.topforecaster.service.UserService
import org.springframework.stereotype.Service

@Service
class ForecastServiceImpl(
        val forecastRepository: ForecastRepository,
        val userService: UserService,
        val matchCalendarService: MatchCalendarService
) : ForecastService {

    override fun addForecast(forecasts: List<ForecastDTO>) {
        forecasts.forEach { forecast ->
            val forecastEntity = Forecast(
                    user = userService.findUser(
                            telegramId = forecast.userDTO.telegramId,
                            chatId = forecast.userDTO.chatId
                    ),
                    match = matchCalendarService.findMatchById(forecast.matchId),
                    hostScore = forecast.hostScore,
                    guestScore = forecast.guestScore
            )
            forecastRepository.save(forecastEntity)
        }
    }
}
