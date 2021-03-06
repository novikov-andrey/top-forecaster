package com.football.topforecaster.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.football.topforecaster.PostgresIT
import com.football.topforecaster.repository.UserRepository
import com.football.topforecaster.util.userDTO
import com.football.topforecaster.util.userEntity
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@Transactional
class UserControllerIT : PostgresIT() {

    @Autowired
    private lateinit var context: WebApplicationContext
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    @Autowired
    private lateinit var userRepository: UserRepository


    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    fun `registerUser - happy path`() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/registration")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(userDTO()))
        )
                .andExpect(status().isOk)

        val users = userRepository.findAll()
        assertEquals(1, users.size)
        assertEquals(userEntity(), users[0])
    }

    @Test
    fun `registerUser - not valid request`() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/registration")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(userDTO().apply { name = "" }))
        )
                .andExpect(status().isBadRequest)

    }

}
