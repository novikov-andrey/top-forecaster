package com.football.topforecaster.repository

import com.football.topforecaster.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>
