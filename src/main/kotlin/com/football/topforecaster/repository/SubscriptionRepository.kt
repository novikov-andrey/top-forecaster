package com.football.topforecaster.repository

import com.football.topforecaster.entity.Subscription
import com.football.topforecaster.entity.enums.Tournament
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Long> {

    fun removeByUserIdAndTournamentAndSeason(userId: Long, tournament: Tournament, season: Int)
}
