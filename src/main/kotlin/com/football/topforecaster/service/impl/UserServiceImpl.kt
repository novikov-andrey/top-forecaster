package com.football.topforecaster.service.impl

import com.football.topforecaster.entity.User
import com.football.topforecaster.repository.UserRepository
import com.football.topforecaster.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        val userRepository: UserRepository
) : UserService {

    override fun registerTelegramUser(user: User) {
        userRepository.save(user)
    }
}
