package com.football.topforecaster.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.football.topforecaster.PostgresIT
import com.football.topforecaster.dto.SubscriptionDTO
import com.football.topforecaster.dto.UserDTO
import com.football.topforecaster.entity.Subscription
import com.football.topforecaster.entity.enums.Tournament
import com.football.topforecaster.repository.SubscriptionRepository
import com.football.topforecaster.repository.UserRepository
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
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@Transactional
open class SubscriptionControllerIT : PostgresIT() {

    @Autowired
    private lateinit var context: WebApplicationContext
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var subscriptionRepository: SubscriptionRepository

    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    open fun `addSubscription - happy path`() {
        userRepository.save(userEntity())

        val subscription = subscriptionEntity()
                .apply {
                    id = 2
                    user = userEntity().apply { id = 2 }
                }

        mockMvc.perform(
                MockMvcRequestBuilders.post("/subscription/new")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(subscriptionDTO()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk)

        val subscriptions = subscriptionRepository.findAll()
        assertEquals(1, subscriptions.size)
        assertEquals(subscription, subscriptions[0])
    }

    @Test
    fun `removeSubscription - happy path`() {
        userRepository.save(userEntity())
        subscriptionRepository.save(subscriptionEntity())

        mockMvc.perform(
                MockMvcRequestBuilders.post("/subscription/remove")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(subscriptionDTO()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk)

        val subscriptions = subscriptionRepository.findAll()
        assertEquals(0, subscriptions.size)
    }

    private fun subscriptionDTO() = SubscriptionDTO(
            userDTO = UserDTO(
                    telegramId = 1,
                    chatId = 1,
                    name = "@test"
            ),
            tournament = "RPL",
            season = 2018
    )

    private fun subscriptionEntity() = Subscription(
            id = 1,
            user = userEntity(),
            tournament = Tournament.RPL,
            season = 2018
    )
}
