package com.kafka.produce.config

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class Config {

    @Value("\${bootstrap.servers}")
    private lateinit var servers : String

    private fun config() : Map<String, Any> {
        val map = HashMap<String, String>()
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer")

        return map
    }

    @Bean
    fun producerJson() : KafkaProducer<String, JsonNode> = KafkaProducer(config())
}