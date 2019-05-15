package com.football.topforecaster.controller

import com.football.topforecaster.dto.SubscriptionDTO
import com.football.topforecaster.entity.Subscription
import com.football.topforecaster.entity.User
import com.football.topforecaster.entity.enums.Tournament
import com.football.topforecaster.service.SubscriptionService
import com.football.topforecaster.service.UserService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("subscription")
class SubscriptionController(
        val subscriptionService: SubscriptionService,
        val userService: UserService
) {

    @PostMapping("/new")
    fun addSubscription(@RequestBody @Valid subscriptionDTO: SubscriptionDTO) {
        subscriptionService.addSubscription(Subscription(
                user = findUser(subscriptionDTO),
                tournament = Tournament.valueOf(subscriptionDTO.tournament),
                season = subscriptionDTO.season
        ))
    }

    @PostMapping("/remove")
    fun removeSubscription(@RequestBody @Valid subscriptionDTO: SubscriptionDTO) {
        subscriptionService.removeSubscription(
                user = findUser(subscriptionDTO),
                tournament = Tournament.valueOf(subscriptionDTO.tournament),
                season = subscriptionDTO.season
        )
    }

    private fun findUser(subscriptionDTO: SubscriptionDTO): User =
            userService.findUser(
                    telegramId = subscriptionDTO.userDTO.telegramId,
                    chatId = subscriptionDTO.userDTO.chatId
            )
}
