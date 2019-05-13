package com.football.topforecaster.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.football.topforecaster.PostgresIT
import com.football.topforecaster.dto.ForecastDTO
import com.football.topforecaster.repository.ForecastRepository
import com.football.topforecaster.repository.MatchCalendarRepository
import com.football.topforecaster.repository.UserRepository
import com.football.topforecaster.util.upcomingMatch
import com.football.topforecaster.util.userDTO
import com.football.topforecaster.util.userEntity
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

class ForecastControllerIT : PostgresIT() {

    @Autowired
    private lateinit var context: WebApplicationContext
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var forecastRepository: ForecastRepository
    @Autowired
    private lateinit var matchCalendarRepository: MatchCalendarRepository

    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }


    @Test
    fun `addForecast - happy path`() {
        matchCalendarRepository.save(upcomingMatch())
        userRepository.save(userEntity())

        mockMvc.perform(
                MockMvcRequestBuilders.post("/forecast/new")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(listOf(forecastDTO())))
        )
                .andExpect(MockMvcResultMatchers.status().isOk)

        val forecasts = forecastRepository.findAll()
        assertEquals(1, forecasts.size)
        assertEquals(4, forecasts[0].hostScore)
        assertEquals(3, forecasts[0].guestScore)
    }

    private fun forecastDTO() = ForecastDTO(
            matchId = 1,
            hostScore = 4,
            guestScore = 3,
            userDTO = userDTO()
    )

}
