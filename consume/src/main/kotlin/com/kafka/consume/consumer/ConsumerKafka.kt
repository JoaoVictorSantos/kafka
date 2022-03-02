package com.kafka.consume.consumer

import com.beust.klaxon.Klaxon
import com.kafka.consume.dto.Person
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class ConsumerKafka {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger("ConsumerKafka")
    }

    @KafkaListener(topics = ["\${topic-person}"], groupId = "\${group-id}")
    fun consumerJson(@Payload data: ConsumerRecord<String, Person>) {
        val record = data.value() as String
        val person = Klaxon().parse<Person>(record)

        LOGGER.info("Consumer Person: ${person.toString()}")
    }

}