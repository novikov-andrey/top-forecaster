package com.football.topforecaster.entity

import javax.persistence.*

@Entity
@Table(name = "`user`")
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid-gen")
        @SequenceGenerator(name = "userid-gen", sequenceName = "user_id_seq", allocationSize = 1)
        var id: Long? = null,
        @Column(name = "telegram_id")
        var telegramId: Long,
        @Column(name = "chat_id")
        var chatId: Long,
        var name: String
)
