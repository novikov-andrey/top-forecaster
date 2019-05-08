package com.football.topforecaster.controller

import com.football.topforecaster.dto.UserDTO
import com.football.topforecaster.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UserController(
        val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid userDTO: UserDTO) {
        userService.registerTelegramUser(userDTO.toUserEntity())
    }
}
