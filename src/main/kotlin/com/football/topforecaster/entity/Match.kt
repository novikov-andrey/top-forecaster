package com.football.topforecaster.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import org.hibernate.annotations.Parameter
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "match_calendar")
data class Match (
        @Id
        @GenericGenerator(
                name = "matchGenerator",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = [
                        Parameter(name = "sequence_name", value = "natch_id_seq"),
                        Parameter(name = "initial_value", value = "1"),
                        Parameter(name = "increment_size", value = "1")
                ]
        )
        @GeneratedValue(generator = "dealGenerator")
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
