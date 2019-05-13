package com.football.topforecaster.util

import com.football.topforecaster.dto.UserDTO
import com.football.topforecaster.entity.User

fun userDTO() = UserDTO(
        telegramId = 1,
        chatId = 1,
        name = "@test"
)

fun userEntity() = User(
        id = 1,
        telegramId = 1,
        chatId = 1,
        name = "@test",
        subscriptions = emptyList(),
        forecasts = emptyList()
)
