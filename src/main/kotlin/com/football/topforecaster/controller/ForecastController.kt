package com.football.topforecaster.controller

import com.football.topforecaster.dto.ForecastDTO
import com.football.topforecaster.service.ForecastService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("forecast")
class ForecastController(
        val forecastService: ForecastService
) {

    @PostMapping("/new")
    fun addForecast(@RequestBody @Valid forecasts: List<ForecastDTO>) {
        forecastService.addForecast(forecasts)
    }
}
