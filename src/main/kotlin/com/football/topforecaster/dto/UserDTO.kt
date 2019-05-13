package com.football.topforecaster.dto

import com.football.topforecaster.entity.User
import javax.validation.constraints.NotBlank

data class UserDTO (
        var telegramId: Long,
        var chatId: Long,
        @get:NotBlank
        var name: String
) {
    fun toUserEntity() = User(
            telegramId = telegramId,
            chatId = chatId,
            name = name,
            subscriptions = emptyList(),
            forecasts = emptyList()
    )
}
