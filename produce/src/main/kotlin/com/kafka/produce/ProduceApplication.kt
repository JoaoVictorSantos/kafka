package com.kafka.produce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProduceApplication

fun main(args: Array<String>) {
	runApplication<ProduceApplication>(*args)
}
