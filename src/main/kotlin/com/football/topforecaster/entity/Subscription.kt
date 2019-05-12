package com.football.topforecaster.entity

import com.football.topforecaster.entity.enums.Tournament
import javax.persistence.*

@Entity
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionid-gen")
    @SequenceGenerator(name = "subscriptionid-gen", sequenceName = "subscription_id_seq", allocationSize = 1)
    var id: Long? = null,
    @Column(name = "user_id")
    var userId: Long,
    @Enumerated(EnumType.STRING)
    var tournament: Tournament
)
