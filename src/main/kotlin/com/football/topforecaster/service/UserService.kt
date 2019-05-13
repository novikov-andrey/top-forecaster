package com.football.topforecaster.service

import com.football.topforecaster.entity.User

interface UserService {
    fun registerTelegramUser(user: User)
    fun findUser(telegramId: Long, chatId: Long): User
}
