package com.football.topforecaster.service

import com.football.topforecaster.entity.User

interface UserService {
    fun registerTelegramUser(user: User)
}
