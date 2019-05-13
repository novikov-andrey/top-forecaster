package com.football.topforecaster.repository

import com.football.topforecaster.entity.Forecast
import org.springframework.data.jpa.repository.JpaRepository

interface ForecastRepository : JpaRepository<Forecast, Long>
