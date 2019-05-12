package com.football.topforecaster.controller

import com.football.topforecaster.dto.SubscriptionDTO
import com.football.topforecaster.entity.Subscription
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
                userId = findUserId(subscriptionDTO),
                tournament = Tournament.valueOf(subscriptionDTO.tournament)
        ))
    }

    @PostMapping("/remove")
    fun removeSubscription(@RequestBody @Valid subscriptionDTO: SubscriptionDTO) {
        subscriptionService.removeSubscription(
                userId = findUserId(subscriptionDTO),
                tournament = Tournament.valueOf(subscriptionDTO.tournament)
        )
    }

    private fun findUserId(subscriptionDTO: SubscriptionDTO): Long =
            userService.findUserId(
                    telegramId = subscriptionDTO.userDTO.telegramId,
                    chatId = subscriptionDTO.userDTO.chatId
            )
}
