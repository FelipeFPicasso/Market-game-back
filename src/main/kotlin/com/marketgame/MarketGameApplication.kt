package com.marketgame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class MarketGameApplication

fun main(args: Array<String>) {
	runApplication<MarketGameApplication>(*args)
}
