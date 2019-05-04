package com.football.topforecaster

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class TopForecasterApplication

fun main(args: Array<String>) {
    runApplication<TopForecasterApplication>(*args)
}
