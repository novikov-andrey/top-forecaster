package com.football.topforecaster.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
class Forecast(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forecastid-gen")
    @SequenceGenerator(name = "forecastid-gen", sequenceName = "forecast_id_seq", allocationSize = 1)
    var id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    var match: Match,
    @Column(name = "host_score")
    var hostScore: Int,
    @Column(name = "guest_score")
    var guestScore: Int,
    var date: LocalDate = LocalDate.now()
)
