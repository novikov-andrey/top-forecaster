package com.football.topforecaster.service

import com.football.topforecaster.entity.Subscription
import com.football.topforecaster.entity.enums.Tournament

interface SubscriptionService {
    fun addSubscription(subscription: Subscription)
    fun removeSubscription(userId: Long, tournament: Tournament)
}
