package com.football.topforecaster.model

import com.football.topforecaster.entity.User
import javax.validation.constraints.NotBlank

data class UserDTO (
        var telegramId: Long,
        var chatId: Long,
        @NotBlank
        var name: String
) {
    fun toUserEntity() = User(
            telegramId = telegramId,
            chatId = chatId,
            name = name
    )
}
