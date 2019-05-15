package com.football.topforecaster.service

import com.football.topforecaster.entity.Subscription
import com.football.topforecaster.entity.User
import com.football.topforecaster.entity.enums.Tournament

interface SubscriptionService {
    fun addSubscription(subscription: Subscription)
    fun removeSubscription(user: User, tournament: Tournament, season: Int)
}
