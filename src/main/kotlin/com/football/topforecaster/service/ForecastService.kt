package com.football.topforecaster.service

import com.football.topforecaster.dto.ForecastDTO

interface ForecastService {
    fun addForecast(forecasts: List<ForecastDTO>)
}
