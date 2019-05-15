package com.football.topforecaster.service.impl

import com.football.topforecaster.entity.Subscription
import com.football.topforecaster.entity.User
import com.football.topforecaster.entity.enums.Tournament
import com.football.topforecaster.repository.SubscriptionRepository
import com.football.topforecaster.service.SubscriptionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubscriptionServiceImpl(
        val subscriptionRepository: SubscriptionRepository
) : SubscriptionService {

    @Transactional
    override fun addSubscription(subscription: Subscription) {
        subscriptionRepository.save(subscription)
    }

    @Transactional
    override fun removeSubscription(user: User, tournament: Tournament, season: Int) {
        subscriptionRepository.removeByUserIdAndTournamentAndSeason(user.id!!, tournament, season)
    }
}
