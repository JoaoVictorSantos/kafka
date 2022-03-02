package com.kafka.consume

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumeApplication

fun main(args: Array<String>) {
	runApplication<ConsumeApplication>(*args)
}
