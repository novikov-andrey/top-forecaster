package com.football.topforecaster.entity

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "match_calendar")
data class Match (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matchid-gen")
        @SequenceGenerator(name = "matchid-gen", sequenceName = "match_id_seq", allocationSize = 1)
        var id: Long? = null,
        var host: String,
        var guest: String,
        var date: LocalDate,
        @Column(name = "start_time")
        var startTime: LocalTime?,
        @Column(name = "host_score")
        var hostScore: Int?,
        @Column(name = "guest_score")
        var guestScore: Int?,
        var round: Int,
        var season: Int
)
